package br.edu.ifpb.dac.projeto.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.dac.projeto.dao.PecaDao;
import br.edu.ifpb.dac.projeto.entities.Compra;
import br.edu.ifpb.dac.projeto.entities.ItemCompra;
import br.edu.ifpb.dac.projeto.entities.Peca;
import br.edu.ifpb.dac.projeto.enumerations.TypesPeca;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopException;
import br.edu.ifpb.dac.projeto.exceptions.PartsShopExceptionHandler;
import br.edu.ifpb.dac.projeto.util.jpa.TransacionalCdi;

public class PecaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PecaDao dao;

	@Inject
	private CompraService compraService;

	public static List<TypesPeca> typesPeca = new ArrayList<TypesPeca>();

	public PecaService() {
		typesPeca = Arrays.asList(TypesPeca.values());
	}

	@TransacionalCdi
	public void add(Peca peca) throws PartsShopException {
		dao.add(peca);
	}

	@TransacionalCdi
	public void remove(Peca peca) throws PartsShopException {
		if(isPecaExiste(peca)){
			dao.remove(peca);
		}
	}

	private boolean isPecaExiste(Peca peca) throws PartsShopExceptionHandler {
		List<ItemCompra> itens = new ArrayList<ItemCompra>();
		for (Compra compra : compraService.findAll()) {
			itens.addAll(compra.getItensCompra());
		}
		for (ItemCompra itemCompra : itens) {
			if (itemCompra.getPeca().equals(peca)) {
				throw new PartsShopExceptionHandler("Peça relacionada a alguma compra não pode ser removida!");
			}
		}
		return true;
	}

	public Peca findById(Long id) {
		return dao.findById(id);
	}

	public Peca update(Peca peca) {
		return dao.update(peca);
	}

	public List<Peca> findAll() {
		return dao.findAll();
	}
}