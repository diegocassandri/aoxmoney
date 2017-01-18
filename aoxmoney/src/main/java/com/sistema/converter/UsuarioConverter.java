package com.sistema.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sistema.model.Usuario;
import com.sistema.repository.Usuarios;
import com.sistema.util.CDIServiceLocator;



@FacesConverter(forClass = Usuario.class,value = "usuarioPickConverter")
public class UsuarioConverter implements Converter {
	// @Inject (ainda não é suportado)
	private Usuarios usuarios;

	public UsuarioConverter() {
		this.usuarios = CDIServiceLocator.getBean(Usuarios.class);
	}

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = this.usuarios.pesquisaPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Usuario usuario = ((Usuario) value);
			return usuario.getCodigo() == null ? null : usuario.getCodigo().toString();
		}
		return null;
	}
}