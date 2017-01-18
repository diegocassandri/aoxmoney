package com.sistema.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sistema.model.Estado;
import com.sistema.repository.Estados;
import com.sistema.util.CDIServiceLocator;





@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter {
	// @Inject (ainda não é suportado)
	private Estados estados;

	public EstadoConverter() {
		this.estados = CDIServiceLocator.getBean(Estados.class);
	}

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estado retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = this.estados.pesquisaPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Estado estado = ((Estado) value);
			return estado.getCodigo() == null ? null : estado.getCodigo().toString();
		}
		return null;
	}
}