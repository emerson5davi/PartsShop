package br.edu.ifpb.dac.projeto.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.services.CompraService;

@Named
@RequestScoped
@FacesConverter(forClass = Compra.class)
public class CompraConverter implements Converter {

	private CompraService compraService = new CompraService();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = Long.parseLong(value);
		return compraService.findById(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Compra) value).getId();
		return (id != null) ? id.toString() : null;
	}
}
