package ca.uottawa.cmcfa039.healthservicesapp;

public class Client extends User {
	public Client (){
		super ();

	}
	public Client (String fName, String lName, String password, String em){
		super (fName, lName, password, em);
	}

	public void changePassowrd (String newPassword){
		this.password = newPassword;
	}
	// This class will be further developed in other deliverables
}