package com.sistema.controller;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.sistema.model.Usuario;
import com.sistema.service.CadastroUsuario;
import com.sistema.service.NegocioException;
import com.sistema.util.FacesMessages;

@Named
@ViewScoped
public class UtilBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String senhaAtual;
    private String novaSenha;
    private String novaSenha2;
    
    @Inject
    private CadastroUsuario cadastrousuario;
    
    @Inject
    private FacesMessages messages;
    
    @Inject
    private LoginController loginController;
    
    private Usuario usuario;
    
    public void alterarSenha() throws NoSuchAlgorithmException, IOException, NegocioException{
    	usuario = loginController.getUsuario();
    	String aux = usuario.getSenha();
    	if(senhaAtual.equals(aux)){
			if(novaSenha.equals(novaSenha2)){
				usuario.setMudarSenha(false);
				usuario.setSenha(novaSenha);
				cadastrousuario.salvar(usuario);
				FacesContext.getCurrentInstance().getExternalContext().redirect("/Home.xhtml");
			} else{
				messages.error("Senhas não conferem!");
				RequestContext.getCurrentInstance().update(Arrays.asList("msgs"));
			}
		}else{
			messages.error("Senha Atual Inválida!");
			RequestContext.getCurrentInstance().update(Arrays.asList("msgs"));
		}
    }
    
    
    
	public String getSenhaAtual() {
		return senhaAtual;
	}
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	public String getNovaSenha2() {
		return novaSenha2;
	}
	public void setNovaSenha2(String novaSenha2) {
		this.novaSenha2 = novaSenha2;
	}
    
    

}
