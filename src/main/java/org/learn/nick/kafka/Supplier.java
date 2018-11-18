package org.learn.nick.kafka;

import java.util.Date;

public class Supplier {

    private int suppplierID;
    private String supplierName;
    private Date supplierDate;

    public Supplier(int suppplierID, String supplierName, Date supplierDate) {
        this.suppplierID = suppplierID;
        this.supplierName = supplierName;
        this.supplierDate = supplierDate;
    }

    public int getSuppplierID() {
        return suppplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public Date getSupplierDate() {
        return supplierDate;
    }
}
