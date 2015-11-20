package lannisters.devcor.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xoma0_000
 */
@Service
public class MailSender {
	private static final String SENDER = "devcor2015@gmail.com";
	@Autowired	
	private  Mail mail;
	
	public void send(String receiver, String subject, String body){
		mail.sendMail(SENDER, receiver, subject, body);
		System.out.println("message successfully sent");
	}
	

}