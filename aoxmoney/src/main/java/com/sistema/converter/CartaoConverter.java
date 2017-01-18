package com.sistema.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sistema.model.Cartao;
import com.sistema.repository.Cartoes;
import com.sistema.util.CDIServiceLocator;


@FacesConverter(forClass = Cartao.class)
public class CartaoConverter implements Converter {
	// @Inject (ainda não é suportado)
	private Cartoes cartoes;

	public CartaoConverter() {
		this.cartoes = CDIServiceLocator.getBean(Cartoes.class);
	}

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cartao retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = this.cartoes.pesquisaPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cartao cartao = ((Cartao) value);
			return cartao.getCodigo() == null ? null : cartao.getCodigo().toString();
		}
		return null;
	}
}