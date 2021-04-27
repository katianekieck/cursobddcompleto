package br.ce.kkati.servicos;

import java.util.Calendar;

import javax.management.RuntimeErrorException;

import br.ce.kkati.entidades.Filme;
import br.ce.kkati.entidades.NotaAluguel;
import br.ce.kkati.entidades.TipoAluguel;
import br.ce.kkati.utils.DateUtils;

public class AluguelService {

	public NotaAluguel alugar(Filme filme, TipoAluguel tipo) {
		if(filme.getEstoque() == 0) 
				throw new RuntimeException("Filme sem estoque");
		
		NotaAluguel nota =new NotaAluguel();
		switch (tipo) {
		case COMUM:
			nota.setPreco(filme.getAluguel());
			nota.setDataEntrega(DateUtils.obterDataDiferencaDias(1));
			nota.setPontuacao(1);
			break;
		case EXTENTIDO:
			nota.setPreco(filme.getAluguel() * 2);
			nota.setDataEntrega(DateUtils.obterDataDiferencaDias(3));
			nota.setPontuacao(2);
			break;
		case SEMANAL:
			nota.setPreco(filme.getAluguel() * 3);
			nota.setDataEntrega(DateUtils.obterDataDiferencaDias(7));
			nota.setPontuacao(3);
			break;

		default:
			break;
		}
		
		filme.setEstoque(filme.getEstoque() -1);
		return nota;
		
		/*	if("extendido".equals(tipo)) {
			nota.setPreco(filme.getAluguel() * 2);
			nota.setDataEntrega(DateUtils.obterDataDiferencaDias(3));
			nota.setPontuacao(2);
		}else if("semanal".equals(tipo)) {
				nota.setPreco(filme.getAluguel() * 3);
				nota.setDataEntrega(DateUtils.obterDataDiferencaDias(7));
				nota.setPontuacao(3);*/
		/*
		 * }else { nota.setPreco(filme.getAluguel());
		 * nota.setDataEntrega(DateUtils.obterDataDiferencaDias(1));
		 * nota.setPontuacao(1); }
		 * 
		 * 
		 * nota.setPreco(filme.getAluguel());
		 * nota.setDataEntrega(DateUtils.obterDataDiferencaDias(1));
		 */
	
		
		//Calendar cal= Calendar.getInstance();
				//cal.add(Calendar.DAY_OF_MONTH, 1);
				//nota.setDataEntrega(cal.getTime());
	
	}
}