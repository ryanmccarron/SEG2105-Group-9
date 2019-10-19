package ca.uottawa.cmcfa039.healthservicesapp;

public abstract class User{
	
	//Class Variable Declarations
	private String password, userName, email;
	private String firstName, lastName;
	
	//Default Constructor
	User(){
		firstName = null;
		lastName = null;
		//userName = null;
		password = null;
		email = null;
	}
	
	//Constructor with passed variables
	User(String fName, String lName, String pass, String mail){
		firstName = fName;
		lastName = lName;
		//userName = uName;
		password = pass;
		email = mail;
	}
	
	//getter for firstName variable
	public String getFirstName(){ return firstName; }
	public void setFirstName(String newFirstName) {this.firstName = newFirstName;}


	//getter for lastName variable
	public String getLastName(){ return lastName; }
	public void setLastName(String newLastName) {this.lastName = newLastName;}

	//getter for userName variable
	/*private String getUserName(){
		return userName;
	}*/

	//getter for password variable
	public String getPassword(){ return password; }
	public void setPassword(String newPassword) {this.password = newPassword;}

	public String getEmail() { return email; }
	public void setEmail(String newEmail) {this.email = newEmail;}


}
	//Setters will be handled in Client class