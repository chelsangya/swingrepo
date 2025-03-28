package sageapp.model;

/**
 *
 * @author sangyakoirala
 */

public class UpdateProfilePictureModel {
    private int uid;
    private byte[] image;
    public UpdateProfilePictureModel( int uid,byte[] image){
        this.image=image;
        this.uid=uid;
    }
    public byte[] getImage(){
        return this.image;
    }
     public int getUid(){
        return this.uid;
    }
}
