package ifma.dcomp.lbd.pedidovenda.model;

public enum TipoPessoa {

	FISICA("Pessoa Física"), 
	JURIDICA("Pessoa Jurídica");
	
	private String tipo;
	
	TipoPessoa(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	
}
