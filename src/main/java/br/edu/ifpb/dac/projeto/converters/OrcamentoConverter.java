package br.edu.ifpb.dac.projeto.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.projeto.entities.Orcamento;
import br.edu.ifpb.dac.projeto.services.OrcamentoService;

@Named
@RequestScoped
@FacesConverter(forClass = Orcamento.class)
public class OrcamentoConverter implements Converter {

	@Inject
	private OrcamentoService orcamentoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = Long.parseLong(value);
		return orcamentoService.findById(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Orcamento) value).getId();
		return (id != null) ? id.toString() : null;
	}
}
