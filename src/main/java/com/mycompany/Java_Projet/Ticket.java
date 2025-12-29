/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Java_Projet;

public class Ticket {
    private int id;
    private Client client;
    private Vol vol;

    public Ticket( Client client, Vol vol) {
        this.client = client;
        this.vol = vol;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Vol getVol() {
        return vol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }
    
    public void displayDetails(){
        System.out.println("Ticket Id:"+id);
        System.out.println("Client Details:");
        client.displayDetails();
        System.out.println("Vol Details");
        vol.displayDetails();
    }

    Object getTicketId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
