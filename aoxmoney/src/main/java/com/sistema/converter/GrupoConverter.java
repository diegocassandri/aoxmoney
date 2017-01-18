package com.sistema.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sistema.model.Grupo;
import com.sistema.repository.Grupos;
import com.sistema.util.CDIServiceLocator;



@FacesConverter(forClass = Grupo.class,value = "grupoPickConverter")
public class GrupoConverter implements Converter {
	// @Inject (ainda não é suportado)
	private Grupos grupos;

	public GrupoConverter() {
		this.grupos = CDIServiceLocator.getBean(Grupos.class);
	}

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = this.grupos.pesquisaPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Grupo grupo = ((Grupo) value);
			return grupo.getCodigo() == null ? null : grupo.getCodigo().toString();
		}
		return null;
	}
}