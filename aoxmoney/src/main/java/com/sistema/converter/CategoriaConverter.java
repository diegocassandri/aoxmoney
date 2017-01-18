package com.sistema.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sistema.model.Categoria;
import com.sistema.repository.Categorias;
import com.sistema.util.CDIServiceLocator;


@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {
	// @Inject (ainda não é suportado)
	private Categorias categorias;

	public CategoriaConverter() {
		this.categorias = CDIServiceLocator.getBean(Categorias.class);
	}

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = this.categorias.pesquisaPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Categoria categoria = ((Categoria) value);
			return categoria.getCodigo() == null ? null : categoria.getCodigo().toString();
		}
		return null;
	}
}