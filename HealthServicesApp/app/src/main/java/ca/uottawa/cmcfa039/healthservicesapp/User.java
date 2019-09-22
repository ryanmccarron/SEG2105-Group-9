public abstract class User{
	
	//Class Variable Declarations
	private String password, userName;
	private Final String FIRST_NAME, LAST_NAME;
	
	//Default Constructor
	User(){
		FIRST_NAME = null;
		LAST_NAME = null;
		userName = null;
		password = null;
	}
	
	//Constructor with passed variables
	User(String fName, String lName, String uName, String pass, String mail){
		FIRST_NAME = fName;
		LAST_NAME = lName;
		userName = uName;
		password = pass;
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
	private String getUserName(){
		return userName;
	}
	
	//getter for password variable
	private String getPassWord(){
		return password;
	}
	
	//Setters will be handled in Client class