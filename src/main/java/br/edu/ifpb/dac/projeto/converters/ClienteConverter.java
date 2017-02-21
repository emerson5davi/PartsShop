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

import br.edu.ifpb.dac.projeto.entities.Cliente;
import br.edu.ifpb.dac.projeto.services.ClienteService;

@Named
@RequestScoped
@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter{

	@Inject
	private ClienteService clienteService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = 0l;
		try {
			id = Long.parseLong(value);
			return clienteService.findById(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format(
					"%s é inválido para o cliente", id)), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Cliente) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
