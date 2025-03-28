/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sageapp.model;

/**
 *
 * @author sangyakoirala
 */
public class AuthData {
    private int uid;
    private String fname;    
    private String lname;
    private String email;
    private String password;
    private byte[] image;
    
    public AuthData(int uid, String fname, String lname, String email, String password, byte[] image){
        this.uid=uid;
        this.email = email;
        this.password = password;
        this.fname=fname;
        this.lname=lname;
        this.image=image;
    }
    
    public int getUid(){
        return this.uid;   
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