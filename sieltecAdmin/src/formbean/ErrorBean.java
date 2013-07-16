package formbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ManagedBean (name="errorBean")
@SessionScoped
public class ErrorBean implements Serializable{
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	String message = "";

	public ErrorBean() {
		super();
		logger.trace("Error Bean instantiated");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}