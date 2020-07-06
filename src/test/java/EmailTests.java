import java.util.List;

import javax.mail.Message;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import util.EmailUtils;

public class EmailTests {

	@BeforeClass
	public static void connectToEmail() {
		try {

			new EmailUtils("tanejashivani1426@gmail.com", "Newyork14@", "smtp.gmail.com",
					EmailUtils.EmailFolder.SENT);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testTextContained() {

		try {

			Message[] messages = EmailUtils.getMessagesBySubject("Welcome to Audible", false, 5);
			boolean isSuccessful = false;
			for (Message email : messages) {
				if (EmailUtils.isTextInMessage(email, "Thanks for subscribing")) {
					isSuccessful = true;
					break;
				}
			}

			Assert.assertTrue(isSuccessful);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testLink() {

		try {
			Message[] messages = EmailUtils.getMessagesBySubject("Welcome to Audible", false, 5);

			String linkUrl = "Harry Potter and the Order of the Phoenix, Book 5";
			
			for (Message email : messages) {
				
				List<String> links = EmailUtils.getUrlsFromMessage(email, linkUrl);
				for(String link: links) {
					
					System.setProperty("webdriver.chrome.driver", "//Users//shivani//Downloads//chromedriver//");
					WebDriver driver = new ChromeDriver();
					driver.get(link);
					
				}
			}
			
			// TODO: continue testing
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

}
