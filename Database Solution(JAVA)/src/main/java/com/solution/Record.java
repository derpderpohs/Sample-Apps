package com.solution;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//javabean representation of Database Record
@Entity
@Table(name="store_order")
public class Record implements Serializable{

    @Id @GeneratedValue
    @Column(name="ID" ,unique = true , nullable = false, insertable = false)
    private int ROW_ID;

    @Column(name="ORDER_DATE", nullable = false)
    private Date ORDER_DATE;

    @Column(name="SHIP_DATE", nullable = false)
    private Date SHIP_DATE;

    @Column(name="SHIP_MODE")
    private String SHIP_MODE; 

    @Column(name="QUANTITY", nullable = false)
    private int QUANTITY;

    @Column(name="DISCOUNT")
    private double DISCOUNT;

    @Column(name="PROFIT" , nullable = false)
    private double PROFIT;

    @Column(name="PRODUCT_ID", unique = true , nullable = false)
    private String PRODUCT_ID;

    @Column(name="CUSTOMER_NAME", nullable = false)
    private String CUSTOMER_NAME;

    @Column(name="CATEGORY", nullable = false)
    private String CATEGORY;

    @Column(name="CUSTOMER_ID", unique = true, nullable = false)
    private String CUSTOMER_ID;

    @Column(name="PRODUCT_NAME")
    private String PRODUCT_NAME;

    @Column(name="ORDER_ID", unique = true, nullable = false)
    private String ORDER_ID;


    public int getROW_ID() {
        return ROW_ID;
    }
    
    public void setROW_ID(int rOW_ID) {
        ROW_ID = rOW_ID;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }
    public void setORDER_ID(String oRDER_ID) {
        ORDER_ID = oRDER_ID;
    }
    public Date getORDER_DATE() {
        return ORDER_DATE;
    }
    public void setORDER_DATE(Date oRDER_DATE) {
        ORDER_DATE = oRDER_DATE;
    }
    public Date getSHIP_DATE() {
        return SHIP_DATE;
    }
    public void setSHIP_DATE(Date sHIP_DATE) {
        SHIP_DATE = sHIP_DATE;
    }
    public String getSHIP_MODE() {
        return SHIP_MODE;
    }
    public void setSHIP_MODE(String sHIP_MODE) {
        SHIP_MODE = sHIP_MODE;
    }
    public int getQUANTITY() {
        return QUANTITY;
    }
    public void setQUANTITY(int qUANTITY) {
        QUANTITY = qUANTITY;
    }
    public double getDISCOUNT() {
        return DISCOUNT;
    }
    public void setDISCOUNT(double dISCOUNT) {
        DISCOUNT = dISCOUNT;
    }
    public double getPROFIT() {
        return PROFIT;
    }
    public void setPROFIT(double pROFIT) {
        PROFIT = pROFIT;
    }
    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }
    public void setPRODUCT_ID(String pRODUCT_ID) {
        PRODUCT_ID = pRODUCT_ID;
    }
    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }
    public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
        CUSTOMER_NAME = cUSTOMER_NAME;
    }
    public String getCATEGORY() {
        return CATEGORY;
    }
    public void setCATEGORY(String cATEGORY) {
        CATEGORY = cATEGORY;
    }
    public String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }
    public void setCUSTOMER_ID(String cUSTOMER_ID) {
        CUSTOMER_ID = cUSTOMER_ID;
    }
    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }
    public void setPRODUCT_NAME(String pRODUCT_NAME) {
        PRODUCT_NAME = pRODUCT_NAME;
    }
}
