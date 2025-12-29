/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Java_Projet;

public class Client {
    private String name;
    private String familyname;
    private String PassportId;
    private String CNI;
    private int age;

    public Client(String name, String familyname, String passportId, String CNI, int age) {
        this.name = name;
        this.familyname = familyname;
        PassportId = passportId;
        this.CNI = CNI;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getFamilyname() {
        return familyname;
    }

    public String getPassportId() {
        return PassportId;
    }

    public String getCNI() {
        return CNI;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public void setPassportId(String PassportId) {
        this.PassportId = PassportId;
    }

    public void setCNI(String CNI) {
        this.CNI = CNI;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void displayDetails(){
        System.out.println("Name:"+name);
        System.out.println("Family Name:"+familyname);
        System.out.println("Passport Id:"+PassportId);
        System.out.println("CNI:"+CNI);
        System.out.println("Age:"+age);
    }
}
