package ifma.dcomp.lbd.pedidovenda.builer;

import ifma.dcomp.lbd.pedidovenda.model.Usuario;

public class UsuarioBuilder {

	private Usuario usuario;
		
	private UsuarioBuilder() { 	}
	
	
	public static UsuarioBuilder umUsuario() {
		
		UsuarioBuilder builder = new UsuarioBuilder();
		
		builder.usuario = new Usuario();
		builder.usuario.setNome("Usuario 1");
		builder.usuario.setSenha("123456");
		builder.usuario.setEmail("usuario@email.com");
		return builder;
	}
	
	public UsuarioBuilder comNome(String nome) {
		usuario.setNome(nome);
		return this;
	}
	
	public UsuarioBuilder possuiEmail(String email) {
		usuario.setEmail(email );
		return this;
	}
	
	
	public Usuario constroi(){
		return usuario;
	}
}