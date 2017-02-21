package br.edu.ifpb.dac.projeto.enumerations;

public enum Grupo {
	ADMIN("Adimistrador"),
	FUNCIONARIO("Funcionário"),
	CLIENTE("Cliente");
	
	private String tipoDeUsuario;
	
	private Grupo(String tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}

	public String getTipoDeUsuario() {
		return tipoDeUsuario;
	}
}