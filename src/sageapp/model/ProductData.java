/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sageapp.model;

/**
 *
 * @author sangyakoirala
 */
public class ProductData {
    public Integer id;
    private int uid;
    private String name;    
    private String description;
    private int price;
    private int stock;
    public ProductData(){}
    public ProductData(int uid,String name, String description, int price, int stock){
        this.uid=uid;
        this.name=name;
        this.description=description;
        this.price=price;
        this.stock=stock;   
    }
     public ProductData(Integer id,int uid,String name, String description, int price, int stock){
         this.id=id;
        this.uid=uid;
        this.name=name;
        this.description=description;
        this.price=price;
        this.stock=stock;   
    }
    public Integer getId(){
        return this.id;
    }
    public int getUid(){
        return this.uid;
    }
       public String getName(){
        return this.name;
    } 
       public String getDescription(){
           return this.description;
       }
       public int getPrice(){
           return this.price;
       }
       public int getStock(){
           return this.stock;
       }
       public void setId(int id){
           this.id=id;
       }
       public void setUid(int uid){
           this.uid=uid;
       }
       public void setName(String name){
           this.name=name;
       }
        public void setDescription(String description){
           this.description=description;
       }
        public void setPrice(int price){
           this.price=price;
       }
        public void setStock(int stock){
           this.stock=stock;
       }
    
}
