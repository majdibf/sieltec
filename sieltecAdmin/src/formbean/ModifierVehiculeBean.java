package formbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import db.Conducteur;
import db.Vehicule;

import service.IManagementService;
@ManagedBean
public class ModifierVehiculeBean {

	//input
		private Long id;
		private String immatriculation;
	
		//output
		
		
		@ManagedProperty(value = "#{managementService}")
		private IManagementService managementService;
		
		@ManagedProperty(value = "#{detailVehiculeBean}")
		private DetailVehiculeBean detailVehiculeBean;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getImmatriculation() {
			return immatriculation;
		}

		public void setImmatriculation(String immatriculation) {
			this.immatriculation = immatriculation;
		}

		public IManagementService getManagementService() {
			return managementService;
		}

		public void setManagementService(IManagementService managementService) {
			this.managementService = managementService;
		}

		public DetailVehiculeBean getDetailVehiculeBean() {
			return detailVehiculeBean;
		}

		public void setDetailVehiculeBean(DetailVehiculeBean detailVehiculeBean) {
			this.detailVehiculeBean = detailVehiculeBean;
		}
		
		public String initialiser(){
			Vehicule v=detailVehiculeBean.getVehicule();
			
			this.id=v.getId();
			this.immatriculation=v.getImmatriculation();
			
			return "modifier_vehicule";
		}
		
		public String update(){
			Vehicule v =new Vehicule(detailVehiculeBean.getVehicule().getId() ,immatriculation , detailVehiculeBean.getVehicule().getVersion());
			managementService.updateVehicule(v);
			return "liste_vehicules";
		}
		
		public String retour(){
			return "detail_vehicule";
		}



}
