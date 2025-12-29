package com.mycompany.Java_Projet;

import java.util.Date;

public class VolInternational extends Vol {



    public VolInternational(String volId, String volNum, String from, String to, int capacity, Date dateDepart, Date dateArrival, double price) {
        super(volId, volNum, from, to, capacity, dateDepart, dateArrival, price, "International");
    }



    @Override
    public void displayDetails() {
        super.displayDetails();
    }
}
