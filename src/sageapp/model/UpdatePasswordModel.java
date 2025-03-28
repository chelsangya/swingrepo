package sageapp.model;

/**
 *
 * @author sangyakoirala
 */

public class UpdatePasswordModel {
    private int uid;
    private String password;
    public UpdatePasswordModel( int uid,String password){
        this.password=password;
        this.uid=uid;
    }
    public String getPassword(){
        return this.password;
    }
     public int getUid(){
        return this.uid;
    }
}
