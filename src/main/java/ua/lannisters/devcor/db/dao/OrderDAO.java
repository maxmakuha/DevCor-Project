package ua.lannisters.devcor.db.dao;

import java.util.List;

import ua.lannisters.devcor.db.model.*;

public interface OrderDAO {

	public void createOrder(Order order);
	
    public List<Order> getAllOrdersOfUser(Player user);
    
    public List<Order> getAllOrdersOfTechnician(Player technician);
    
    public List<Order> getAllOrdersForRoom(Room room);
    
    public List<Order> getAllOrders();
    
    public Order getOrder(int orderId);
    
    public void changeOrderExecutionStatus(Order order, ExecutionStatus newExecutionStatus);
    
    public void editOrder(
    		Order order,
    		ProblemType newProblemType,
    		String newMessage,
    		Room newRoom,
    		UrgencyStatus newUrgencyStatus);
    
    public void changeOrderTechnician(Order order, Player newTechnician);
    
    public void changeOrderUrgencyStatus(Order order, UrgencyStatus newUrgencyStatus);
    
    public void deleteOrder(int orderId);
	
}
