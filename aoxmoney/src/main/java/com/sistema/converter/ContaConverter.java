package com.sistema.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sistema.model.Conta;
import com.sistema.repository.Contas;
import com.sistema.util.CDIServiceLocator;




@FacesConverter(forClass = Conta.class)
public class ContaConverter implements Converter {
	// @Inject (ainda não é suportado)
	private Contas contas;

	public ContaConverter() {
		this.contas = CDIServiceLocator.getBean(Contas.class);
	}

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Conta retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = this.contas.pesquisaPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Conta conta = ((Conta) value);
			return conta.getCodigo() == null ? null : conta.getCodigo().toString();
		}
		return null;
	}
}