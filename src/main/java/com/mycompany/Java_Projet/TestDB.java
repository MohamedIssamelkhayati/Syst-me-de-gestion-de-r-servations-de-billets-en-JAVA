package com.mycompany.Java_Projet;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDB {
    public static void main(String[] args) throws SQLException {
        DbManager.getConnection();
    }
}
