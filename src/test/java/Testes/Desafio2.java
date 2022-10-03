package Testes;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class Desafio2 {

	static WebDriver driver;
	String url = "https://www.grocerycrud.com/v1.x/demo/my_boss_is_in_a_hurry/bootstrap";
	
	
	@Before
	public void setUp() throws Exception {

		System.out.println("****** TESTE INICIADO ******");
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		assertEquals("Grocery CRUD Demo - My Boss Is In A Hurry", driver.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		try {
			LocalDate data = LocalDate.now();
			TakesScreenshot ashot = ((TakesScreenshot) driver);
			File scrFile = ashot.getScreenshotAs(OutputType.FILE);
			File destFile = new File("./src/evidencia/" +  "2 " + " " + "Excluído com sucesso " +  "Data " + data + ".png");

			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
		driver.quit();
		
	}


		@Test
		public void test2DeletarCadastro() throws InterruptedException {
			WebElement element = driver.findElement(By.id("switch-version-select"));
			Select combo = new Select(element);
			combo.selectByVisibleText("Bootstrap V4 Theme");
			String textoElement = driver.findElement(By.xpath("//option[@value='/v1.x/demo/my_boss_is_in_a_hurry/bootstrap-v4'][contains(.,'Bootstrap V4 Theme')]")).getText();
			assertEquals("Bootstrap V4 Theme", textoElement);
			driver.findElement(By.name("customerName")).sendKeys("Teste Sicredi");
			Thread.sleep(10000);
			driver.findElement(By.xpath("//input[contains(@class,'select-row')]")).click();
			driver.findElement(By.xpath("//a[contains(@class,'btn btn-outline-dark delete-selected-button')]")).click();
			String msgDeletar = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/div/div[2]/p[2]")).getText();
			assertEquals("Are you sure that you want to delete this 1 item?", msgDeletar);
			driver.findElement(By.xpath("//button[contains(@class,'btn btn-danger delete-multiple-confirmation-button')]")).click();
			Thread.sleep(10000);
			String msgConfirma = driver.findElement(By.xpath("/html/body/div[3]/span[3]/p")).getText();
			assertEquals("Your data has been successfully deleted from the database.", msgConfirma);

		}



}
