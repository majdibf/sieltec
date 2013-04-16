package formbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean (name="errorBean")
@SessionScoped
public class ErrorBean implements Serializable{
	
	String message = "";

	public ErrorBean() {
		super();
		System.out.println("Error Bean instantiated");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}