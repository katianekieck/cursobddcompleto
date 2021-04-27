package br.ce.kkati.steps;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;



public class InserirContaSteps {
	
	private WebDriver driver; 
	
	@Dado("que estou acessando a aplicação")
	public void que_estou_acessando_a_aplicação() {
		driver= new ChromeDriver();
		driver.get("https://srbarriga.herokuapp.com");		
	}

	@Quando("^informo o usuário \"([^\"]*)\"$")
	public void informo_o_usuário(String arg1) {
	 driver.findElement(By.id("email")).sendKeys(arg1);
	}

	@Quando("^a senha \"([^\"]*)\"$")
	public void a_senha(String arg1) {
		 driver.findElement(By.name("senha")).sendKeys(arg1);
	}

	@Quando("seleciono entrar")
	public void seleciono_entrar() {
		 driver.findElement(By.tagName("button")).click();
	}

	@Então("visualizo a página inicial")
	public void visualizo_a_página_inicial() {
		String texto= driver.findElement(By.xpath("//div[@class='alert alert-success'])")).getText();
		 Assert.assertEquals("Bem Vindo, Vagner!", texto);
	}

	@Quando("^seleciono Contas$")
	public void seleciono_contas() {
		 driver.findElement(By.linkText("Contas")).click();
		}

	@Quando("seleciono Adicionar")
	public void seleciono_adicionar() {
		 driver.findElement(By.linkText("Adicionar")).click();
	}

	@Quando("^informo a conta \"([^\"]*)\"$")
	public void informo_a_conta(String arg1) {
		 driver.findElement(By.id("nome")).sendKeys(arg1); 
		
	}

	@Quando("seleciono Salvar")
	public void seleciono_salvar() {
		 driver.findElement(By.tagName("button")).click();
	}

	@Então("a conta é inserida com sucesso")
	public void a_conta_é_inserida_com_sucesso() {
		String texto= driver.findElement(By.xpath("//div[@class='alert alert-success'])")).getText();
		 Assert.assertEquals("Conta adicionada com sucesso!", texto);
	}

	@Então("sou notificar que o nome da conta é obrigatório")
	public void sou_notificar_que_o_nome_da_conta_é_obrigatório() {
		String texto= driver.findElement(By.xpath("//div[@class='alert alert-danger'])")).getText();
		 Assert.assertEquals("Informe o nome da conta", texto);
		
	}

	@Então("sou notificado que já existe uma conta com esse nome")
	public void sou_notificado_que_já_existe_uma_conta_com_esse_nome() {
		String texto= driver.findElement(By.xpath("//div[@class='alert alert-danger'])")).getText();
		 Assert.assertEquals("Ja existe uma conta com esse nome!", texto);
	}
	
	@Então("recebo a mensagem \"([^\"]*)\"$")
	public void recebo_a_mensagem(String arg1) {
		String texto= driver.findElement(By.xpath("//div[starts-with(@class='alert alert-'])")).getText();
		 Assert.assertEquals(arg1, texto);
	}

	//@Before (order = 10)
	@Before
	public void inicio() {
		System.out.println("Começou aqui");
			}
	//@Before (order = 1)
	@Before
	public void inicio2() {
		System.out.println("Começou aqui");
		
		

	}
	
	//@After(order = 1, value = {"~@funcionais"})
	@After
	public void screenshot(Scenario cenario) {
	File file= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
	FileUtils.copyFile(file, new File("target/screenshot/"+cenario.getId()+".jpg"));
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
		
	@After
	//@After(order = 0, value = {"~@funcionais"})
	private void fecharBrowser() {
		driver.quit();
		System.out.println("Terminando aqui");
		
	}
}
