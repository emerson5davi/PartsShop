package br.edu.ifpb.dac.projeto.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Divida;
import br.edu.ifpb.dac.projeto.services.DividaService;

@Named
@RequestScoped
@FacesConverter(forClass=Divida.class)
public class DividaConverter implements Converter{

	@Inject
	private DividaService dividaService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent componet, String value) {
		
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = 0l;
		
		try {
			id = Long.parseLong(value);
			return dividaService.findById(id);
		} catch (NumberFormatException e){
			throw new ConverterException(new FacesMessage(String.format(
					"%s é inválido para a dívida", id)), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componet, Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Divida) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
