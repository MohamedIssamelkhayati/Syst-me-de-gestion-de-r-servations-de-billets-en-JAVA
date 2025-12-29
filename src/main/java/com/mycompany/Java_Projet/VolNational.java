package com.mycompany.Java_Projet;


import java.util.Date;

public class VolNational extends Vol {


    public VolNational(String volId, String volNum, String from, String to, int capacity, Date dateDepart, Date dateArrival, double price) {
        super(volId, volNum, from, to, capacity, dateDepart, dateArrival, price, "National");
    }



    @Override
    public void displayDetails() {
        super.displayDetails();
    }
}
