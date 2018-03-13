package co.com.pruebas.saber.app.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;

@ManagedBean
public class UserLoginView {

	private String username;

	private String password;

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

	public void login(ActionEvent event) {
		FacesMessage message = null;
		boolean loggedIn = false;

		if (username != null && username.equals("admin") && password != null
				&& password.equals("admin")) {
			loggedIn = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Bienvenido", username);
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Loggin Error", "Credenciales incorrectas");
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
	}
}