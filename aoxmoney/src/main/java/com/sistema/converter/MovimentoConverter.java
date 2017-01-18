package com.sistema.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sistema.model.Movimento;
import com.sistema.repository.Movimentos;
import com.sistema.util.CDIServiceLocator;


@FacesConverter(forClass = Movimento.class)
public class MovimentoConverter implements Converter {
	// @Inject (ainda não é suportado)
	private Movimentos movimentos;

	public MovimentoConverter() {
		this.movimentos = CDIServiceLocator.getBean(Movimentos.class);
	}

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Movimento retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = this.movimentos.pesquisaPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Movimento movimento = ((Movimento) value);
			return movimento.getCodigo() == null ? null : movimento.getCodigo().toString();
		}
		return null;
	}
}