package lannisters.devcor.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String createNewOrder(@ModelAttribute Order order, Model m, Principal principal) throws SQLException {
		order.setExecutionStatusId(1);
		order.setCreationDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		order.setDueDate(new Timestamp(order.getCreationDate().getTime()
				+ urgencyStatusesService.getUrgencyStatusMinutes(order.getUrgencyStatusId()) * 60 * 1000));
		order.setAuthorId(playersService.getPlayerIdByEmail(principal.getName()));
		order.setTechnicianId(roomsService.getTechnicianIdByRoomId(order.getRoomId()));
		order.setOverdue("N");
		if (order.getDeviceId() == -1) {
			order.removeDevice();
		}
		ordersService.addOrder(order);
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
	public String updateOrder(OrderAndComment orderAndComment) throws SQLException {
		Order order = ordersService.getOrderById(orderAndComment.getOrder().getOrderId());
		int oldStatusId = order.getExecutionStatusId();

		orderAndComment.getOrder()
				.setTechnicianId(roomsService.getTechnicianIdByRoomId(orderAndComment.getOrder().getRoomId()));
		orderAndComment.getOrder().setDueDate(new Timestamp(orderAndComment.getOrder().getCreationDate().getTime()
				+ urgencyStatusesService.getUrgencyStatusMinutes(orderAndComment.getOrder().getUrgencyStatusId()) * 60 * 1000));

		ordersService.updateOrder(orderAndComment.getOrder());

		if (orderAndComment.getComment() != null && orderAndComment.getComment().getComment() != null) {
			commentsService.addComment(orderAndComment.getComment());
			int newStatusId = orderAndComment.getOrder().getExecutionStatusId();

			if (newStatusId > 3 && oldStatusId != newStatusId) {
				order = ordersService.getOrderById(orderAndComment.getOrder().getOrderId());
				mail.statusEmail(order);
			}
			mail.commentEmail(orderAndComment);
		}

		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/dashboard/order/delete/id/{id}", method = RequestMethod.GET)
	public String deleteOrder(@PathVariable("id") int orderId) throws SQLException {
		ordersService.deleteOrder(orderId);
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
}