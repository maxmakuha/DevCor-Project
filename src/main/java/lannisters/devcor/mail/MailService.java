package lannisters.devcor.mail;

import lannisters.devcor.entity.Order;
import lannisters.devcor.entity.Player;
import lannisters.devcor.service.PlayersService;
import lannisters.devcor.service.PlayersServiceImpl;

/**
 * @author xoma0_000
 */
public class MailService extends Mail{
	private static String testEmail = "xoma02@gmail.com";
	private static final String REGISTRATION = "Authorization Information";
	private static final String ORDER_CREATED= "Order created";
	private static final String STATUS_CHANGE= "Status on your order has changed";
	private static final String ADMINISTRATOR= "Room: 1-101; E-mail: admin@ukma.kiev.ua;"
			+ " Phone number: 444-44-44, 8-093-999-99-99";
	private static  MailSender sender = new MailSender();
	
	public static void main(String[] args){
	MailService a = new MailService();
	a.rEmail(testEmail, "12345678");
	}
	
	/**
	 * It will send an e-mail notification to a new player, to inform him 
	 * that his account has been created.
	 * @param receiver - The one who will get email
	 * @param password - password created by administrator
	 */
	public void rEmail(String receiver, String password){
		String first = "Welcome to our system! \nYou have been registered in DevCor system "
				+ "with \nLogin: ";
		String second = "\nPassword: ";
		String third = "\nLater you can change your password. If you have any questions "
				+ "be sure to contact with administrator:\n";
		sender.send(receiver, REGISTRATION, first+receiver+second+password+third+ADMINISTRATOR);
	}
	/**
	 * It will send an e-mail notification to the user, to inform him 
	 * about status changing of his order.
	 * @param order - An order which status had been changed
	 */
	public void sEmail(Order order){
		String first = "Status on your order from "+ order.getCreationDate()+""
				+ "has changed to "+order.getExecutionStatus();
		PlayersService service = new PlayersServiceImpl();
		int playerId = order.getAuthorId();
		Player player = service.getPlayerById(playerId);
		String receiver = player.getPlayerEmail();
		sender.send(receiver, STATUS_CHANGE, first);
	}
	/**
	 * It will send an e-mail notification to both user and technician,
	 * who are concerned to this order, to inform them that the 
	 * new Order has been created.
	 * @param order - A newly created order
	 */
	public void oEmail(Order order){
		PlayersService service = new PlayersServiceImpl();
		int userId = order.getAuthorId();
		Player user = service.getPlayerById(userId);
		String receiver1 = user.getPlayerEmail();
		int technicianId = order.getTechnicianId();
		Player technician = service.getPlayerById(technicianId);
		String receiver2 = technician.getPlayerEmail();
		String first = "Your order has been succesfully created! "
				+ "Technician responsible for it is "+technician.getFirstName()+""
						+ " "+technician.getLastName()+". It is predicted to be "
								+ "made by "+order.getDueDate();
		sender.send(receiver1, ORDER_CREATED, first);
		String second = "There is an order for you! It must be done by "+order.getDueDate();
		sender.send(receiver2, ORDER_CREATED, second);
	}
	
	
}
