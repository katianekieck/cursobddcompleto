package br.ce.kkati.runners;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.core.backend.CucumberBackendException;
import io.cucumber.core.snippets.SnippetType;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(io.cucumber.junit.Cucumber.class)
@CucumberOptions( features = "src/test/resources/features/inserir_conta.feature", 
glue = "br.ce.kkati.steps", 
tags = "@unitários",
//tags = {"@unitários"}, 
plugin = {"pretty", "html:target/report-html", "json:target/report-json"}, 
monochrome = true,  strict = true, snippets = CAMELCASE, 
dryRun = false

)

public class Runner {
@BeforeClass
public static void reset() {
	 WebDriver driver= new ChromeDriver();
	driver.get("https://srbarriga.herokuapp.com");		
	 driver.findElement(By.id("email")).sendKeys("a@a");
	 driver.findElement(By.name("senha")).sendKeys("a");
	 driver.findElement(By.tagName("button")).click();
	 driver.findElement(By.linkText("reset")).click();
	 driver.quit();
}
}
