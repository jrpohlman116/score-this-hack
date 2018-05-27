package models;

public class AdminID {
    public String username;
    public String password;
    public String confPassword;

    public AdminID(){
        username = "";
        password = "";
        confPassword = "";
    }

    public AdminID(String username, String password, String confPassword){
        this.username = username;
        this.password = password;
        this.confPassword = confPassword;
    }
}
