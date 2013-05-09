package dao;

import java.util.List;

import db.SouscriptionAlerte;

public interface ISouscriptionAlerteDao {
	
	public Long insert (SouscriptionAlerte abonne);

	public List<SouscriptionAlerte> findByIdLigne();
	

}
