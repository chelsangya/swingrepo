package sageapp.model;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sangyakoirala
 */

public class BillData {
    private int billId;
    private int uid;
    private String name;
    private String phone;
    private int totalPrice;
    private Timestamp date_of_creation; //it is TimeStamp
    private Map<ProductData,Integer> products;
    public BillData(){}
    public BillData(int billId,int uid,String name, String phone, int totalPrice,Timestamp date_of_creation, Map<ProductData,Integer> products){
        this.billId=billId;
        this.date_of_creation=date_of_creation;
        this.uid=uid;
        this.name=name;
        this.phone=phone;
        this.products=products;
        this.totalPrice=totalPrice;
    }
     public int getBillId(){
        return this.billId;
    }
    public int getUid(){
        return this.uid;
    }
    public String getName(){
        return this.name;
    }
    public String getPhone(){
        return this.phone;
    }
    public int getTotalPrice(){
        return this.totalPrice;
    }
    public Timestamp getDateOfCreation(){
        return this.date_of_creation;
    }
    public Map<ProductData,Integer> getProductQuantityMap(){
        return this.products;
    }


}
