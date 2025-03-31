package sageapp.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sangyakoirala
 */

public class BillModel {
    private int uid;
    private String phone;
    private int totalPrice;
    private Map<ProductData,Integer> products;
    public BillModel(){}
    public BillModel(int uid, String phone, int totalPrice, Map<ProductData,Integer> products){
        this.uid=uid;
        this.phone=phone;
        this.products=products;
        this.totalPrice=totalPrice;
    }
    public int getUid(){
        return this.uid;
    }
    public String getPhone(){
        return this.phone;
    }
    public int getTotalPrice(){
        return this.totalPrice;
    }
    public Map<ProductData,Integer> getProductQuantityMap(){
        return this.products;
    }
       public Map<Integer, Integer> getProductMap() {
        Map<Integer, Integer> productMap = new HashMap<>();
        for (Map.Entry<ProductData, Integer> entry : products.entrySet()) {
            productMap.put(entry.getKey().getId(), entry.getValue());
        }
        return productMap;
    }

    public void printProducts() {
        for (Map.Entry<ProductData, Integer> entry : products.entrySet()) {
            System.out.println("Product ID: " + entry.getKey().getId() + ", Quantity: " + entry.getValue());
        }
    }
//   Need to send id of each product from productdata and quantity to create bills table
}
