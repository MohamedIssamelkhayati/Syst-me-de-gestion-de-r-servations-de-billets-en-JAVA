package com.mycompany.Java_Projet;

import java.util.Date;

public class Vol {
    private String VolId;
    private String VolNum;
    private String from;
    private String to;
    private int capacity;
    private Date dateDepart;
    private Date dateArrival;
    private double price;
    private String vol_type; // New field to store the type of flight

    public Vol(String volId, String volNum, String from, String to, int capacity, Date dateDepart, Date dateArrival, double price, String vol_type) {
        VolId = volId;
        VolNum = volNum;
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.dateDepart = dateDepart;
        this.dateArrival = dateArrival;
        this.price = price;
        this.vol_type = vol_type; // Initialize the vol_type field
    }

    public String getVolId() {
        return VolId;
    }

    public String getVolNum() {
        return VolNum;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getCapacity() {
        return capacity;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public double getPrice() {
        return price;
    }

    public String getVolType() {
        return vol_type;
    }

    public void setVolId(String VolId) {
        this.VolId = VolId;
    }

    public void setVolNum(String VolNum) {
        this.VolNum = VolNum;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVolType(String vol_type) {
        this.vol_type = vol_type;
    }

    public void displayDetails() {
        System.out.println(" ");
        System.out.println("Flight ID: " + VolId);
        System.out.println("Flight Number: " + VolNum);
        System.out.println("Capacity: " + capacity);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Departure Date: " + dateDepart);
        System.out.println("Arrival Date: " + dateArrival);
        System.out.println("Price: " + price + " Dh");
        System.out.println("Type: " + vol_type); // Display the flight type
        System.out.println("**************************************************");

    }
}
