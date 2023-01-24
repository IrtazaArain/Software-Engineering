package com.example.taxgo;

public class Property_Inquiry_DataModal {
    String property_type;
    String city;
    String address;
    String land_area;
    String covered_area;
    String construction_type;

    public Property_Inquiry_DataModal(String property_type, String city, String address,
    String land_area, String covered_area, String construction_type) {
        this.property_type = property_type;
        this.city = city;
        this.address = address;
        this.land_area = land_area;
        this.covered_area = covered_area;
        this.construction_type = construction_type;
    }

    public String getProperty_Type() {
        return property_type;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getLand_Area() {
        return land_area;
    }

    public String getCovered_Area() {
        return covered_area;
    }

    public String getConstruction_Type() {
        return construction_type;
    }
}
