package fj.king.model;

import java.util.List;

import javax.validation.Valid;

public class IdentityForm {
	
	@Valid
	private List<Identity> identities;

	public List<Identity> getIdentities() {
		return identities;
	}
	
	public void setIdentities(List<Identity> identities2) {
		this.identities = identities2;
		
	}
}
