package lannisters.devcor.mail;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xoma0_000
 */
public class MailSender {
	private static final String SENDER = "followthecursor@gmail.com";
	private static Mail mail = (Mail) new ClassPathXmlApplicationContext("applicationContext.xml").getBean("Mail");
	
	public void send(String receiver, String subject, String body){
		mail.sendMail(SENDER, receiver, subject, body);
		System.out.println("message successfully sent");
	}
}