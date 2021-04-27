package br.ce.kkati.steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;

import br.ce.kkati.entidades.Filme;
import br.ce.kkati.entidades.NotaAluguel;
import br.ce.kkati.entidades.TipoAluguel;
import br.ce.kkati.servicos.AluguelService;
import br.ce.kkati.utils.DateUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AlugarFilmeSteps {

	private Filme filme;
	private AluguelService aluguel= new AluguelService();
	private NotaAluguel nota;
	private String erro;
	private TipoAluguel tipoAluguel;
		
	@Dado("^um filme com estoque de (\\d+) unidades$")
	public void um_filme_com_estoque_de_unidades(int arg1) throws Throwable {
	  filme = new Filme();
	  filme.setEstoque(arg1);
	}

	@Dado("^que o preço do aluguel seja R\\$ (\\d+)$")
	public void que_o_preço_do_aluguel_seja_r$(int arg1) throws Throwable {
		 filme.setAluguel(arg1);
	}
	
	
	@Dado("um filme")
	public void um_filme(DataTable table)throws Throwable {
	 Map <String, String> map= table.asMap(String.class, String.class);
	  filme = new Filme();
	  filme.setEstoque(Integer.parseInt(map.get("estoque")));
	  filme.setAluguel(Integer.parseInt(map.get("preco")));
	  String tipo = map.get("tipo");
	  tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL: tipo.equals("extendido")? TipoAluguel.EXTENTIDO: TipoAluguel.COMUM ;
	 

	}

	@Quando("alugar")
	public void alugar() throws Throwable {
		try {
			nota= aluguel.alugar(filme, tipoAluguel);
			}catch (Exception e) {
				erro = e.getMessage();			
		}
	}

	@Então("^o preço do aluguel será R\\$ (\\d+)$")
	public void o_preço_do_aluguel_será_r$(int arg1) throws Throwable {
	   Assert.assertEquals(arg1,nota.getPreco());
	}

	/*
	 * @Então("^a data de entrega será no dia seguinte$") public void
	 * a_data_de_entrega_será_no_dia_seguinte() throws Throwable { Calendar cal =
	 * Calendar.getInstance(); cal.add(Calendar.DAY_OF_MONTH, 1);
	 * 
	 * Date dataRetorno= nota.getDataEntrega(); Calendar calRetorno =
	 * Calendar.getInstance(); calRetorno.setTime(dataRetorno);
	 * Assert.assertEquals(cal.get(Calendar.DAY_OF_MONTH),
	 * calRetorno.get(Calendar.DAY_OF_MONTH));
	 * Assert.assertEquals(cal.get(Calendar.MONTH), calRetorno.get(Calendar.MONTH));
	 * Assert.assertEquals(cal.get(Calendar.YEAR), calRetorno.get(Calendar.YEAR)); }
	 */

	@Então("^o estoque do filme será (\\d+) undidade$")
	public void o_estoque_do_filme_será_undidade(int arg1) throws Throwable{
	Assert.assertEquals(arg1, filme.getEstoque());
	}

	
	@Então("^não será possível por falta de estoque$")
	public void não_será_possível_por_falta_de_estoque() {
	  Assert.assertEquals("Filme sem estoque", erro);
	}

	@Então("^o estoque do filme sera {int} unidade$")
	public void o_estoque_do_filme_sera_unidade(int arg1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


	@Dado("^que o tipo do aluguel seja (.*)$")
	public void que_o_tipo_do_aluguel_seja_extendido(String tipo) {
		tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL: tipo.equals("extendido")? TipoAluguel.EXTENTIDO: TipoAluguel.COMUM ;
	}

	@Então("^a data de entrega será em (\\d+) dia?$")
	public void a_data_de_entrega_será_em_dias(int arg1) throws Throwable{
	   Date dataEsperada= DateUtils.obterDataDiferencaDias(arg1);
	   Date dataReal= nota.getDataEntrega();
	   
	   DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
	   
	   Assert.assertEquals(format.format(dataEsperada), format.format(dataReal));
	  // Assert.assertEquals(dataEsperada, dataReal);
	}

	@Então("a pontuação será de {int} pontos")
	public void a_pontuação_será_de_pontos(int arg1) {
		Assert.assertEquals(arg1, nota.getPontuacao());
	}

}
