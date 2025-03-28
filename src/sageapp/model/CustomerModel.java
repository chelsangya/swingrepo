package sageapp.model;

/**
 *
 * @author sangyakoirala
 */

public class CustomerModel {
    private String phone;
    private String name;
    public CustomerModel(String phone, String name){
        this.phone = phone;
        this.name = name;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getName(){
        return this.name;
    }
}
