package com.sistema.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sistema.model.Periodo;
import com.sistema.repository.Periodos;
import com.sistema.util.CDIServiceLocator;


@FacesConverter(forClass = Periodo.class)
public class PeriodoConverter implements Converter {
	// @Inject (ainda não é suportado)
	private Periodos periodos;

	public PeriodoConverter() {
		this.periodos = CDIServiceLocator.getBean(Periodos.class);
	}

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Periodo retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = this.periodos.pesquisaPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Periodo periodo = ((Periodo) value);
			return periodo.getCodigo() == null ? null : periodo.getCodigo().toString();
		}
		return null;
	}
}