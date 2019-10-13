public class Client extends User {
	public Client (){
		super ();

	}
	public Client (String fName, String lName, String uName, String password, String em){
		super (fName, lName, uName, password, em);
	}

	public void changePassowrd (String newPassword){
		password = newPassword;
	}
	// This class will be further developed in other deliverables
}