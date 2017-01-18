package com.sistema.controller;



import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sistema.model.Staff;
import com.sistema.model.Usuario;
import com.sistema.repository.Usuarios;
import com.sistema.security.CommonPage;
import com.sistema.security.UserForm;
import com.sistema.util.CriptografaSenha;
import com.sistema.util.JsfUtil;



@Named	
@SessionScoped
public class LoginController implements java.io.Serializable {

    private static final long serialVersionUID = 1553957937211717410L;

    private String username;
    private String password;
    private boolean loggedIn;

    @Inject
    private Usuarios usuarios;
    
    private Usuario usuario = new Usuario();
    private Staff staff;
    private String ipAddress;
    private String nomeCompleto;
  
    public LoginController() {
    }


    @PostConstruct
    public void init() {
      
    }
    
    
    public void login() {
        try {
            if (!username.equals("") && !username.equals(null) && !password.equals("") && !password.equals(null)) {
                usuario = usuarios.login(username,CriptografaSenha.criptografa( password));
            }

            if (usuario != null) {
                UserForm form = new UserForm();
                loggedIn = true;

                nomeCompleto = usuario.getNomeCompleto();
                
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

                ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }

                form.setSessionId(request.getSession().getId());
                form.setSessionCreatedTime(new Date(session.getCreationTime()));
                form.setUserIpAdress(ipAddress);
                form.setSession(request.getSession());
                form.setStaff(staff);

                CommonPage.addStaff(form, request.getSession().getId());

                session.setAttribute("staff", usuario);
                
                if(usuario.getMudarSenha() == true){
                	FacesContext.getCurrentInstance().getExternalContext().redirect("/AlterarSenha.xhtml");
                } else {
                	FacesContext.getCurrentInstance().getExternalContext().redirect("/Home.xhtml");
                }
                
            } else {
                JsfUtil.addExclamationMessage("Credenciais Inválidas!");
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            CommonPage.removeStaff(staff);
            loggedIn = false;
            staff = null;
            FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    

    public String getUsername() {
        return username;
    }

  
    public void setUsername(String username) {
        this.username = username;
    }

  
    public String getPassword() {
        return password;
    }

  
    public void setPassword(String password) {
        this.password = password;
    }

    public Staff getStaff() {
        return staff;
    }

  
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

   
    public boolean isLoggedIn() {
        return loggedIn;
    }

   
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

   
    public int getSessionCount() {
        return CommonPage.getUserSessionSize();
    }

    public List<Staff> getStaffList() {
        return CommonPage.getStaffList();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    

  
    public String getNomeCompleto() {
		return nomeCompleto;
	}


	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
