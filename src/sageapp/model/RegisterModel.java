
package sageapp.model;

/**
 *
 * @author sangyakoirala
 */
public class RegisterModel {
    private String fname;    
    private String lname;
    private String email;
    private String password;
    private byte[] image;
    public RegisterModel(String fname, String lname, String email, String password, byte[] image){
        this.email = email;
        this.password = password;
        this.fname=fname;
        this.lname=lname;
        this.image=image;
    }
    
    public String getFname(){
        return this.fname;
    }  
    
    public String getLname(){
        return this.lname;
    }  
    
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    } 
    public byte[] getImage(){
        return this.image;
    }
    
           
}