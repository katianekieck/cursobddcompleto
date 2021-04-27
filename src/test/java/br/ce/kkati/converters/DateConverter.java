package br.ce.kkati.converters;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.awt.List;
import javax.xml.transform.Transformer;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableTransformer;
import java.util.Locale;
import static java.util.Locale.ENGLISH;

import cucumber.api.*;

public abstract class DateConverter extends Transformer {

	public Date transform(String arg0) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date retorno = format.parse(arg0);
			return retorno;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
