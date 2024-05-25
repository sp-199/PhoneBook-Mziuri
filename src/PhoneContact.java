import java.util.Scanner;
import java.util.ArrayList;
public class PhoneContact {
    private String name, lastName, number, email;
    public PhoneContact(String name, String lastName, String number, String email) {
        this.name = name;
        this.lastName = lastName;
        this.number = number;
        this.email = email;
    }
    public String displayContact(){
        String info = "Name: "+getName()+";\n"+"Lastname: "+getLastName()+";\n"+"Number: "+getNumber()+";\n"+"Email: "+getEmail()+"\n";
        return info;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public String getNumber() {
        return number;
    }
    public String getEmail() {
        return email;
    }
}

