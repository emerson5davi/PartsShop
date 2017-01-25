package br.edu.ifpb.dac.projeto.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CLIENTE")
@DiscriminatorValue("Cliente")
@NamedQueries({ 
	@NamedQuery(name = "Cliente.findByCPF", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf")
})
public class Cliente extends Pessoa{

	private static final long serialVersionUID = 1L;
	
}