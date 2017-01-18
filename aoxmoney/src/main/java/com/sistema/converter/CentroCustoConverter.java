package com.sistema.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sistema.model.CentroCusto;
import com.sistema.repository.CentrosCustos;
import com.sistema.util.CDIServiceLocator;




@FacesConverter(forClass = CentroCusto.class)
public class CentroCustoConverter implements Converter {
	// @Inject (ainda não é suportado)
	private CentrosCustos centrosCustos;

	public CentroCustoConverter() {
		this.centrosCustos = CDIServiceLocator.getBean(CentrosCustos.class);
	}

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		CentroCusto retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = this.centrosCustos.pesquisaPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			CentroCusto centroCusto = ((CentroCusto) value);
			return centroCusto.getCodigo() == null ? null : centroCusto.getCodigo().toString();
		}
		return null;
	}
}