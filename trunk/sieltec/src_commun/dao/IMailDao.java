package dao;

import java.util.List;


public interface IMailDao {

	public void sendMail(String to, String subject, String body);

}
