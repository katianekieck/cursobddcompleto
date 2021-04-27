package br.ce.kkati.steps;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AprenderCucumberSteps {
	@Dado("que eu criei o arquivo corretamente")
	public void queEuCrieiOArquivoCorretamente() throws Throwable {

	}

	@Quando("executa-lo")
	public void executaLo() throws Throwable {

	}

	@Entao("a especificacao deve finalizar com sucesso")
	public void aEspecificacaoDeveFinalizarComSucesso() throws Throwable {

	}

	public int contador = 0;

	@Dado("que o valor do contador é {int}")
	public void queOValorDoContadoré(int int1) {
		contador = int1;
	}

	@Quando("eu incrementar {int}")
	public void euIncrementar(int int1) {
		contador = contador + int1;
	}

	@Então("o valor do contador sera {int}")
	public void oValorDoContadorSera(int int1) {
		System.out.println(int1);
		System.out.println(contador);
		// System.out.println(int1 ==contador);
		// Assert.assertTrue(int1 ==contador);
		Assert.assertEquals(int1, contador);
		throw new RuntimeException();
	}

	Date entrega = new Date();

	//@Dado("^que a entrega é dia (.*)$")
	//public void queAEntregaEDia(@Transform(DateConverter.class) Date data) throws Throwable {
	//entrega = data;
	//System.out.println(entrega);
	
	@Dado("^que a entrega é dia (\\d+)/(\\d+)/(\\d+)$")
		public void queAEntregaEDia(int dia, int mes, int ano) throws Throwable {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, dia);
		cal.set(Calendar.MONTH, mes - 1);
		cal.set(Calendar.YEAR, ano);
		entrega = cal.getTime();
	}

	//@Quando("^a entrega atrasar em (\\d+) (.+)$")
	@Quando("^a entrega atrasar em (\\d+) (dia|dias|mes|meses)$")
	public void aEntregaAtrasarEmDias(int arg1, String tempo) throws Throwable {
		Calendar cal = Calendar.getInstance();
		cal.setTime(entrega);
		if(tempo.equals("dias")) {
			cal.add(Calendar.DAY_OF_MONTH, arg1);
			}
		if(tempo.equals("meses")) {
			cal.add(Calendar.MONTH, arg1);
			}
		entrega = cal.getTime();
	}
		
	@Então("^a entrega sera efetuada em (\\d{2}\\/\\d{2}\\/\\d{4})$")
	public void aEntregaSeraEfetuadaEm(String data) throws Throwable {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = format.format(entrega);
		Assert.assertEquals(data, dataFormatada);
	}

	
	
	

	//@Dado("^que o ticket (especial)? é A.(\\d+)$")
	@Dado("^que o ticket (especial)? é (A.\\d{3})$")	
	public void queOTicketEAF(String tipo, String arg1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	//@Dado("^que o valor da passagem é R\\$ (.*)$")
	@Dado("^que o valor da passagem é R\\$ (\\d+),(\\d+)$")
	public void que_o_valor_da_passagem_é_r$(Double numero) {
	System.out.println(numero); 
	}

	//@Dado("^que o nome do passageiro é \"(.*)\"$")
	@Dado("^que o nome do passageiro é \"(.{5,20})\"$")
	public void que_o_nome_do_passageiro_é(String string) {
	   
	}

	@Dado("que o telefone do passageiro é (9\\d{3}-\\d{4})$")
	public void que_o_telefone_do_passageiro_é(String telefone) {
	}

	@Quando("criar os steps")
	public void criar_os_steps() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Então("o teste vai funcionar")
	public void o_teste_vai_funcionar() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
