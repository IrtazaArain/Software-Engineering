package com.example.taxgo;

public class Vehicle_Inquiry_DataModal {

    String vehicle_class;
    String purchase_type;
    String ownership_status;
    String price;
    String seating_capacity;
    String import_purchase_date;
    String engine_size;

    public Vehicle_Inquiry_DataModal(String vehicle_class, String purchase_type, String ownership_status,
                                     String price, String seating_capacity, String import_purchase_date,String engine_size) {
        this.vehicle_class = vehicle_class;
        this.purchase_type = purchase_type;
        this.ownership_status = ownership_status;
        this.price = price;
        this.seating_capacity = seating_capacity;
        this.import_purchase_date = import_purchase_date;
        this.engine_size = engine_size;
    }

    public String getVehicle_Class() {
        return vehicle_class;
    }

    public String getPurchase_Type() {
        return purchase_type;
    }

    public String getOwnership_status() {
        return ownership_status;
    }

    public String getPrice() {
        return price;
    }

    public  String getSeating_Capacity() {return  seating_capacity;}

    public  String getImport_Purchase_Date() {return  import_purchase_date;}

    public  String getEngine_Size() {return  engine_size;}
}
