package br.edu.ifpb.dac.projeto.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.ItemCompra;
import br.edu.ifpb.dac.projeto.services.ItemCompraService;

@Named
@RequestScoped
@FacesConverter(forClass = ItemCompra.class)
public class ItemPecaConverter implements Converter {

	private ItemCompraService itemPecaService = new ItemCompraService();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = Long.parseLong(value);
		return itemPecaService.findById(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((ItemCompra) value).getId();
		return (id != null) ? id.toString() : null;
	}
}