package com.cst438;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

@SpringBootTest
public class E2E_newStudent {
	public static final String CHROME_DRIVER_FILE_LOCATION 
                          = "C:/chromedriver_win32/chromedriver.exe";
	public static final String URL = "http://localhost:3000";
	public static final String STUDENT_NAME = "EndtoEndTest";
	public static final String STUDENT_EMAIL = "E2E@csumb.edu";
	public static final int SLEEP_DURATION = 1000; // 1 second.
	
	
	@Autowired
	StudentRepository studentRepository;
	
	@Test
	public void testStudent() throws Exception {



		//TODO update the property name for your browser 
		System.setProperty("webdriver.chrome.driver",
                     CHROME_DRIVER_FILE_LOCATION);
	//TODO update the class ChromeDriver()  for your browser
	// For chromedriver 111 need to specify the following options 
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");	


           WebDriver driver = new ChromeDriver(ops);
		
		try {
			WebElement we;
			
			driver.get(URL);
			// must have a short wait to allow time for the page to download 
			Thread.sleep(SLEEP_DURATION);
			
			// find and click the student button
			we = driver.findElement(By.id("studentButton"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			// enter a student name
			we = driver.findElement(By.name("name"));
			we.sendKeys(STUDENT_NAME);
			
			// enter a student email
			we = driver.findElement(By.name("email"));
			we.sendKeys(STUDENT_EMAIL);
			
			// find and click the submit button
			we = driver.findElement(By.id("submit"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			Student student = studentRepository.findByEmail(STUDENT_EMAIL);
			
			assertEquals(student.getEmail(), STUDENT_EMAIL);
			assertEquals(student.getName(), STUDENT_NAME);			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		


	
		} finally {
			driver.close();
			driver.quit();
		}
	}
		

}