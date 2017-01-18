package com.sistema.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sistema.model.Cidade;
import com.sistema.repository.Cidades;
import com.sistema.util.CDIServiceLocator;




@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter {
	// @Inject (ainda não é suportado)
	private Cidades cidades;

	public CidadeConverter() {
		this.cidades = CDIServiceLocator.getBean(Cidades.class);
	}

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cidade retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = this.cidades.pesquisaPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cidade cidade = ((Cidade) value);
			return cidade.getCodigo() == null ? null : cidade.getCodigo().toString();
		}
		return null;
	}
}