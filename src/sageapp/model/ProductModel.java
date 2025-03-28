/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sageapp.model;

/**
 *
 * @author sangyakoirala
 */
public class ProductModel {
    private Integer uid;
    private int id; 
    private String name;    
    private String description;
    private int price;
    private int stock;
    public ProductModel(int uid,String name, String description, int price, int stock){
        this.uid=uid;
        this.name=name;
        this.description=description;
        this.price=price;
        this.stock=stock;   
    }
     public ProductModel(Integer id,int uid, String name, String description, int price, int stock) {
        this.id = id;
        this.uid=uid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;   
    }
    public ProductModel(){}
       public String getName(){
        return this.name;
    } 
       public int getUid(){
           return this.uid;
       }
       public Integer getId(){
           return this.id;
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
        public void setUid(int uid) {
        this.uid = uid;
    }
    public void setId(Integer id) {
        this.id = id;
    }



    public void setName(String name) {
        this.name = name;
    }

  

    public void setDescription(String description) {
        this.description = description;
    }

 

    public void setPrice(int price) {
        this.price = price;
    }



    public void setStock(int stock) {
        this.stock = stock;
    }
}
