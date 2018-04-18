package co.com.pruebas.saber.app.beans;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;

import co.com.pruebas.saber.app.connection.Database;

@ManagedBean
public class UserBean {

	private String username;
	private String password;
	private String mesaj;
	private boolean oturum = false;

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

	public boolean isOturum() {
		return oturum;
	}

	public void setOturum(boolean oturum) {
		this.oturum = oturum;
	}
	
	public String getMesaj() {
		return mesaj;
	}

	public void setMesaj(String mesaj) {
		this.mesaj = mesaj;
	}

	public String validarCredenciales() throws SQLException {
		Database database = new Database();
		boolean res = database.validarUsuario(username, password);		
		if (res) {
			oturum = true;
			loginTrue();
			return "index.xhtml?faces-redirect=true";
		} else {
			oturum = false;
            loginFalse();
			return "login.xhtml";
		}

	}

	public void loginTrue() {
		FacesMessage message = null;
		boolean loggedIn = true;

		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido",
				username);
		FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
	}

	public void loginFalse() {
		FacesMessage message = null;
		boolean loggedIn = false;

		message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error",
				"Usuario y/o Contraseña incorrectos");
		FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
	}	
}
