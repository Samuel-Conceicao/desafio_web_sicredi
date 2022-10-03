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

public class Desafio {

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
			File destFile = new File("./src/evidencia/" +  "1 " + " " + "Cadastrado com sucesso " +  "Data " + data + ".png");

			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
		driver.quit();
		
	}

	@Test
	public void test1selecionarOpcao() throws InterruptedException {
		WebElement element = driver.findElement(By.id("switch-version-select"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Bootstrap V4 Theme");
		String textoElement = driver.findElement(By.xpath("//option[@value='/v1.x/demo/my_boss_is_in_a_hurry/bootstrap-v4'][contains(.,'Bootstrap V4 Theme')]")).getText();
		assertEquals("Bootstrap V4 Theme", textoElement);
		driver.findElement(By.xpath("//a[@class='btn btn-default btn-outline-dark'][contains(.,'Add Record')]")).click();
		driver.findElement(By.id("field-customerName")).sendKeys("Teste Sicredi");
		driver.findElement(By.name("contactLastName")).sendKeys("Teste");
		driver.findElement(By.name("contactFirstName")).sendKeys("Samuel Conceicao");
		driver.findElement(By.id("field-phone")).sendKeys("51 9999-9999");
		driver.findElement(By.id("field-addressLine1")).sendKeys("Av Assis Brasil, 3970");
		driver.findElement(By.id("field-addressLine2")).sendKeys("Torre D");
		driver.findElement(By.name("city")).sendKeys("Porto Alegre");
		driver.findElement(By.name("state")).sendKeys("RS");
		driver.findElement(By.id("field-postalCode")).sendKeys("91000-000");
		driver.findElement(By.name("country")).sendKeys("Brasil");
		driver.findElement(By.xpath("//input[contains(@name,'salesRepEmployeeNumber')]")).sendKeys("300");
		driver.findElement(By.xpath("//input[contains(@name,'creditLimit')]")).sendKeys("200");
		driver.findElement(By.xpath("//button[@class='btn btn-secondary btn-success b10'][contains(.,'Save')]")).click();
		Thread.sleep(10000);
		String mensagem = driver.findElement(By.xpath("//div[@class='report-div success bg-success'][contains(.,'Your data has been successfully stored into the database. Edit Record or Go back to list')]")).getText();
		assertEquals("Your data has been successfully stored into the database. Edit Record or Go back to list", mensagem);



	}


}
