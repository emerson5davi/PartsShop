package br.edu.ifpb.dac.projeto.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.services.PecaService;

@Named
@RequestScoped
@FacesConverter(forClass=Peca.class)
public class PecaConverter implements Converter{

	private PecaService pecaService = new PecaService();

	/**
	 * 
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = 0l;
		try {
			id = Long.parseLong(value);
			return pecaService.findById(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format(
					"%s é inválido para a peça", id)), e);
		}
	}

	/**
	 * 
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Peca) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
