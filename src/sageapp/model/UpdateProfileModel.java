package sageapp.model;

/**
 *
 * @author sangyakoirala
 */

public class UpdateProfileModel {
    private String email;
    private String firstName;
    private String lastName;
    public UpdateProfileModel(String email, String firstName, String lastName){
        this.email = email;
        this.firstName = firstName;
        this.lastName= lastName;
    }
    public String getEmail(){
        return this.email;
    }
    public String getFirstName(){
        return this.firstName;
    }
     public String getLastName(){
        return this.lastName;
    }
}
