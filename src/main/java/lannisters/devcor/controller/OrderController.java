package lannisters.devcor.controller;

import java.security.Principal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lannisters.devcor.entity.Order;
import lannisters.devcor.service.DevicesService;
import lannisters.devcor.service.OrdersService;
import lannisters.devcor.service.PlayersService;
import lannisters.devcor.service.ProblemTypesService;
import lannisters.devcor.service.RoomsService;
import lannisters.devcor.service.UrgencyStatusesService;

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
	
	@RequestMapping(value = "/createOrder", method = RequestMethod.GET)
	public String loadCreateOrderPage(Model m){
		Order order = new Order();
		m.addAttribute("order", order);
		m.addAttribute("problemTypes", problemTypesService.getAllProblemTypes());
		m.addAttribute("rooms", roomsService.getAllRooms());
		m.addAttribute("urgencyStatuses", urgencyStatusesService.getAllUrgencyStatuses());
		return "createOrder";
	}
	
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public String showOrderCreateResult(@ModelAttribute Order order, Model m, Principal principal) throws SQLException{
		order.setExecutionStatusId(1);
		order.setCreationDate(new Date(Calendar.getInstance().getTimeInMillis()));
		order.calcDueDate();
		order.setAuthorId(playersService.getPlayerIdByEmail(principal.getName()));
		order.setTechnicianId(roomsService.getTechnicianIdByRoomId(order.getRoomId()));
		order.setOverdue("N");
		if(order.getDeviceId() == -1){
			order.removeDevice();
		}
		ordersService.addOrder(order);
		return "redirect:/dashboard";
	}
	
	@RequestMapping(value = "/singleOrder", method = RequestMethod.POST)
	public String showSingleOrder(@RequestParam("orderId") int orderId, Model m) throws SQLException{
		m.addAttribute("order", ordersService.getOrderById(orderId));
		m.addAttribute("problemTypes", problemTypesService.getAllProblemTypes());
		m.addAttribute("rooms", roomsService.getAllRooms());
		m.addAttribute("urgencyStatuses", urgencyStatusesService.getAllUrgencyStatuses());
		m.addAttribute("technicians", playersService.getAllTechnicians());
		m.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority());
		return "singleOrder";
	}
	
	@RequestMapping(value = "/getRoomDevices", method = RequestMethod.GET)
	public String getRoomDevices(@RequestParam("roomId") int roomId, Model m) throws SQLException{
		m.addAttribute("devices", devicesService.getAllDevicesOfRoom(roomId));
		return "getRoomDevices";
	}
}
