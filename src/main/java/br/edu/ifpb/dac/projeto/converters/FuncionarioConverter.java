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

import br.edu.ifpb.dac.projeto.entities.Funcionario;
import br.edu.ifpb.dac.projeto.services.FuncionarioService;

@Named
@RequestScoped
@FacesConverter(forClass=Funcionario.class)
public class FuncionarioConverter implements Converter{

	@Inject
	private FuncionarioService funcionarioService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = 0l;
		try {
			id = Long.parseLong(value);
			return funcionarioService.findById(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format(
					"%s é inválido para o funcionario", id)), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Funcionario) value).getId();
		return (id != null) ? id.toString() : null;
	}
}