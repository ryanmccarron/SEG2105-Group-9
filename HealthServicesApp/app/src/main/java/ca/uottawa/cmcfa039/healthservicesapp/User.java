package ca.uottawa.cmcfa039.healthservicesapp;

public abstract class User{
	
	//Class Variable Declarations
	public String password, userName, email;
	public String FIRST_NAME, LAST_NAME;
	
	//Default Constructor
	User(){
		FIRST_NAME = null;
		LAST_NAME = null;
		//userName = null;
		password = null;
		email = null;
	}
	
	//Constructor with passed variables
	User(String fName, String lName, String pass, String mail){
		FIRST_NAME = fName;
		LAST_NAME = lName;
		//userName = uName;
		password = pass;
		email = mail;
	}
	
	//getter for FIRST_NAME variable
	private String getFirstName(){
		return FIRST_NAME;
	}
	
	//getter for LAST_NAME variable
	private String getLastName(){
		return LAST_NAME;
	}
	
	//getter for userName variable
	/*private String getUserName(){
		return userName;
	}*/
	
	//getter for password variable
	private String getPassWord(){
		return password;
	}

	private String getEmail() { return email; }
}
	//Setters will be handled in Client class