package lannisters.devcor.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lannisters.devcor.entity.Comment;
import lannisters.devcor.entity.Order;
import lannisters.devcor.mail.MailService;
import lannisters.devcor.service.CommentsService;
import lannisters.devcor.service.DevicesService;
import lannisters.devcor.service.OrdersService;
import lannisters.devcor.service.PlayersService;
import lannisters.devcor.service.ProblemTypesService;
import lannisters.devcor.service.RoomsService;
import lannisters.devcor.service.UrgencyStatusesService;
import lannisters.devcor.util.OrderAndComment;
import lannisters.devcor.util.SimpleOrder;

@Controller
public class OrderController {
	
	@Autowired
	private DevicesService devicesService;

	@Autowired
	private RoomsService roomsService;

	@Autowired
	private PlayersService playersService;

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private ProblemTypesService problemTypesService;

	@Autowired
	private UrgencyStatusesService urgencyStatusesService;

	@Autowired
	private MailService mail;

	@Autowired
	private CommentsService commentsService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showDashboard(Model model, Principal principal) throws SQLException {
		switch (SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next()
				.getAuthority()) {
		case "ROLE_USER":
			model.addAttribute("orders", ordersService.getALlOrdersOfUser(principal.getName()));
			break;
		case "ROLE_TECHNICIAN":
			model.addAttribute("orders", ordersService.getAllOrdersOfTechnician(principal.getName()));
			break;
		case "ROLE_ADMIN":
			model.addAttribute("orders", ordersService.getAllOrdersSorted());
			break;
		default:
			break;
		}
		return "dashboard";
	}

	@RequestMapping(value = "/dashboard/order/create", method = RequestMethod.GET)
	public String showOrderAddingForm(Model m) {
		Order order = new Order();
		m.addAttribute("order", order);
		m.addAttribute("problemTypes", problemTypesService.getAllProblemTypes());
		m.addAttribute("rooms", roomsService.getAllRooms());
		m.addAttribute("urgencyStatuses", urgencyStatusesService.getAllUrgencyStatuses());
		return "createOrder";
	}

	@RequestMapping(value = "/dashboard/order/create", method = RequestMethod.POST)
	public String createNewOrder(@ModelAttribute Order order, Model m, Principal principal,
			RedirectAttributes redirectAttributes) throws SQLException {
		order.setExecutionStatusId(1);
		order.setCreationDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		order.setDueDate(new Timestamp(order.getCreationDate().getTime()
				+ urgencyStatusesService.getUrgencyStatusMinutes(order.getUrgencyStatusId()) * 60 * 1000));
		order.setAuthorId(playersService.getPlayerIdByEmail(principal.getName()));
		order.setTechnicianId(roomsService.getTechnicianIdByRoomId(order.getRoomId()));
		if (order.getDeviceId() == -1) {
			order.removeDevice();
		}
		ordersService.addOrder(order);
		redirectAttributes.addFlashAttribute("message", "Order created successfully!");
		mail.orderCreatEmail(order);
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "dashboard/order/id/{id}", method = RequestMethod.GET)
	public String viewOrder(@PathVariable("id") int orderId, Model m) throws SQLException {
		m.addAttribute("orderAndComment", new OrderAndComment(ordersService.getOrderById(orderId), new Comment()));
		m.addAttribute("problemTypes", problemTypesService.getAllProblemTypes());
		m.addAttribute("rooms", roomsService.getAllRooms());
		m.addAttribute("urgencyStatuses", urgencyStatusesService.getAllUrgencyStatuses());
		m.addAttribute("technicians", playersService.getAllTechnicians());
		m.addAttribute("comments", commentsService.getAllCommentsOfOrder(orderId));
		return "viewOrder";
	}

	@RequestMapping(value = "/dashboard/order/id/{id}", method = RequestMethod.POST)
	public String updateOrder(OrderAndComment orderAndComment, RedirectAttributes redirectAttributes)
			throws SQLException {
		Order oldOrder = ordersService.getOrderById(orderAndComment.getOrder().getOrderId());
		int oldStatusId = oldOrder.getExecutionStatusId();

		if (oldOrder.getRoomId() != orderAndComment.getOrder().getRoomId()) {
			orderAndComment.getOrder()
					.setTechnicianId(roomsService.getTechnicianIdByRoomId(orderAndComment.getOrder().getRoomId()));
		}
		if (oldOrder.getExecutionStatusId() != orderAndComment.getOrder().getExecutionStatusId()) {
			orderAndComment.getOrder().setDueDate(new Timestamp(orderAndComment.getOrder().getCreationDate().getTime()
					+ urgencyStatusesService.getUrgencyStatusMinutes(orderAndComment.getOrder().getUrgencyStatusId())
							* 60 * 1000));
		}

		ordersService.updateOrder(orderAndComment.getOrder());

		if (orderAndComment.getComment() != null && !orderAndComment.getComment().getComment().isEmpty()) {
			commentsService.addComment(orderAndComment.getComment());
			int newStatusId = orderAndComment.getOrder().getExecutionStatusId();

			if (newStatusId > 3 && oldStatusId != newStatusId) {
				mail.statusEmail(ordersService.getOrderById(orderAndComment.getOrder().getOrderId()));
			}
			mail.commentEmail(orderAndComment);
		}
		redirectAttributes.addFlashAttribute("message", "Order edited successfully!");
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/dashboard/order/delete/id/{id}", method = RequestMethod.GET)
	public String deleteOrder(@PathVariable("id") int orderId, RedirectAttributes redirectAttributes)
			throws SQLException {
		ordersService.deleteOrder(orderId);
		redirectAttributes.addFlashAttribute("message", "Order deleted successfully!");
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/getRoomDevices", method = RequestMethod.GET)
	public String getRoomDevices(@RequestParam("roomId") int roomId, Model m) throws SQLException {
		m.addAttribute("devices", devicesService.getAllDevicesOfRoom(roomId));
		return "getRoomDevices";
	}

	@RequestMapping(value = "/getDuplicateOrdersRoom", method = RequestMethod.GET)
	public String getDuplicateOrders(@RequestParam("roomId") int roomId, Model m) throws SQLException {
		m.addAttribute("orders", ordersService.getAllOrdersOfRoomNoDevice((roomId)));
		return "getDuplicateOrdersRoom";
	}

	@RequestMapping(value = "/getDuplicateOrdersDevice", method = RequestMethod.GET)
	public String getDuplicateOrdersDevice(@RequestParam("roomId") int roomId, @RequestParam("deviceId") int deviceId,
			Model m) throws SQLException {

		if (deviceId == -1) {
			m.addAttribute("orders", ordersService.getAllOrdersOfRoomNoDevice((roomId)));
			return "getDuplicateOrdersRoom";
		} else {
			m.addAttribute("orders", ordersService.getAllOrdersOfRoomWithDevice(roomId));
			m.addAttribute("device", devicesService.getDeviceById(deviceId));
			return "getDuplicateOrdersDevice";
		}
	}

	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public String calendar(Model m, Principal principal) throws SQLException {
		m.addAttribute("orders", ordersService.getAllOrdersOfTechnician(principal.getName()));
		return "calendar";
	}

	@RequestMapping(value = "/calendar2", method = RequestMethod.GET)
	public String calendar2(Model m, Principal principal) throws SQLException {
		m.addAttribute("orders", ordersService.getAllOrdersOfTechnician(principal.getName()));
		return "calendar2";
	}

	@RequestMapping(value = "/calendar3", method = RequestMethod.GET)
	public String calendar3(Model m, Principal principal) throws SQLException {
		m.addAttribute("orders", ordersService.getAllOrdersOfTechnician(principal.getName()));
		return "calendar3";
	}


	@SuppressWarnings("deprecation")
	@RequestMapping("/simple")
	public @ResponseBody List<SimpleOrder> getDay(Principal principal) throws SQLException {
		List<SimpleOrder> orders = new ArrayList<SimpleOrder>();
		for (Order order : ordersService.getAllOrdersOfTechnician(principal.getName())) {
			if (order.getExecutionStatusId() < 3) {
				int orderId = order.getOrderId();
				String description = order.getDescription();
				Timestamp dueDate = order.getDueDate();
				orders.add(new SimpleOrder(orderId, description, dueDate.getDate(), dueDate.getMonth(),
						dueDate.getYear() + 1900, dueDate.getHours(), dueDate.getMinutes()));
			}
		}
		return orders;
	}
}