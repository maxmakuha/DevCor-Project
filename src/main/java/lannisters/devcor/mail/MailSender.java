package lannisters.devcor.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xoma0_000
 */
public class MailSender {
	private static final String SENDER = "followthecursor@gmail.com";
	private static final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static final Mail MAIL = (Mail) CONTEXT.getBean("Mail");
	
	public void send(String receiver, String subject, String body){
		MAIL.sendMail(SENDER, receiver, subject, body);
		System.out.println("success");
	}
}