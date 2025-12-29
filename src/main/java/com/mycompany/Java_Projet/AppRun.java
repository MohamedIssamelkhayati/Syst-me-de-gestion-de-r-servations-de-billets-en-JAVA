package com.mycompany.Java_Projet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AppRun {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n-------------------MENU-------------------");
            System.out.println("1 - Show available flights");
            System.out.println("2 - Add a flight");
            System.out.println("3 - Edit a flight");
            System.out.println("4 - Delete a flight");
            System.out.println("5 - Book a ticket");
            System.out.println("6 - Edit a ticket");
            System.out.println("7 - Print a ticket");
            System.out.println("8 - Cancel a ticket");
            System.out.println("9 - Exit");
            System.out.println("------------------------------------------");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showFlights();
                    break;
                case 2:
                    addFlight();
                    break;
                case 3:
                    editFlight();
                    break;
                case 4:
                    deleteFlight();
                    break;
                case 5:
                    bookTicket();
                    break;
                case 6:
                    editTicket();
                    break;
                case 7:
                    printTicket();
                    break;
                case 8:
                    cancelTicket();
                    break;
                case 9:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 9.");
            }
        }
    }

    private static void showFlights() {
        System.out.println("Available Flights:");
        List<Vol> flights = DbManager.getVols();
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
        } else {
            for (Vol flight : flights) {
                flight.displayDetails();
            }
        }

    }

    private static void addFlight() {
        System.out.println(" ");
        System.out.println("Select the type of flight:");
        System.out.println("1 - National Flight");
        System.out.println("2 - International Flight");
        System.out.print("Enter your choice: ");
        int flightType = scanner.nextInt();

        System.out.print("Enter flight ID: ");
        String flightId = scanner.next();
        System.out.print("Enter flight number: ");
        String flightNum = scanner.next();
        System.out.print("Enter flight capacity: ");
        int capacity = scanner.nextInt();
        System.out.print("Enter departure location: ");
        String from = scanner.next();
        System.out.print("Enter arrival location: ");
        String to = scanner.next();
        System.out.print("Enter departure date (yyyy-MM-dd): ");
        String departureDateStr = scanner.next();
        System.out.print("Enter arrival date (yyyy-MM-dd): ");
        String arrivalDateStr = scanner.next();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        Date departureDate = null;
        Date arrivalDate = null;

        try {
            departureDate = new SimpleDateFormat("yyyy-MM-dd").parse(departureDateStr);
            arrivalDate = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            return;
        }

        Vol flight;
        if (flightType == 1) {
            flight = new VolNational(flightId, flightNum, from, to, capacity, departureDate, arrivalDate, price);
        } else if (flightType == 2) {
            flight = new VolInternational(flightId, flightNum, from, to, capacity, departureDate, arrivalDate, price);
        } else {
            System.out.println("Invalid flight type.");
            return;
        }

        DbManager.createVol(flight);
        System.out.println("Flight added successfully.");
    }

    private static void editFlight() {
        System.out.print("Enter flight ID to edit: ");
        String flightId = scanner.next();
        boolean found = false;
        for (Vol flight : DbManager.getVols()) {
            if (flight.getVolId().equals(flightId)) {
                System.out.print("Enter new flight number: ");
                flight.setVolNum(scanner.next());
                System.out.print("Enter new flight capacity: ");
                flight.setCapacity(scanner.nextInt());
                System.out.print("Enter new departure location: ");
                flight.setFrom(scanner.next());
                System.out.print("Enter new arrival location: ");
                flight.setTo(scanner.next());
                System.out.print("Enter new departure date (yyyy-MM-dd): ");
                String departureDateStr = scanner.next();
                System.out.print("Enter new arrival date (yyyy-MM-dd): ");
                String arrivalDateStr = scanner.next();
                System.out.print("Enter new price: ");
                flight.setPrice(scanner.nextDouble());

                Date departureDate = null;
                Date arrivalDate = null;

                try {
                    departureDate = new SimpleDateFormat("yyyy-MM-dd").parse(departureDateStr);
                    arrivalDate = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDateStr);
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                    return;
                }

                flight.setDateDepart(departureDate);
                flight.setDateArrival(arrivalDate);

                DbManager.updateVol(flight);
                System.out.println("Flight updated successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Flight with ID " + flightId + " not found.");
        }
    }

    private static void deleteFlight() {
        System.out.print("Enter flight ID to delete: ");
        String flightId = scanner.next();
        boolean deleted = DbManager.deleteVol(flightId);
        if (deleted) {
            System.out.println("Flight deleted successfully.");
        } else {
            System.out.println("Flight with ID " + flightId + " not found.");
        }
    }

    private static void bookTicket() {
        System.out.println("Available Flights:");
        showFlights();

        System.out.print("Enter flight ID to book a ticket: ");
        String flightId = scanner.next();
        Vol selectedFlight = null;
        for (Vol flight : DbManager.getVols()) {
            if (flight.getVolId().equals(flightId)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight with ID " + flightId + " not found.");
            return;
        }

        System.out.print("Enter client name: ");
        String name = scanner.next();
        System.out.print("Enter client family name: ");
        String familyName = scanner.next();
        System.out.print("Enter client age: ");
        int age = scanner.nextInt();
        System.out.print("Enter client passport ID: ");
        String passportId = scanner.next();
        System.out.print("Enter client CNI: ");
        String cni = scanner.next();

        Client client = new Client(name, familyName, passportId, cni, age);

        Ticket ticket = new Ticket(client, selectedFlight);
        DbManager.createClient(client);
        DbManager.createTicket(ticket);

        System.out.println("Ticket booked successfully.");
    }

    private static void editTicket() {
        System.out.print("Enter ticket ID to edit: ");
        int ticketId = scanner.nextInt();
        boolean found = false;
        for (Ticket ticket : DbManager.getTickets()) {
            if (ticket.getId() == ticketId) {
                ticket.displayDetails();
                System.out.println("Enter new flight details:");
                editFlight();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Ticket with ID " + ticketId + " not found.");
        }
    }

    private static void printTicket() {
        System.out.print("Enter ticket ID to print: ");
        int ticketId = scanner.nextInt();
        boolean found = false;
        for (Ticket ticket : DbManager.getTickets()) {
            if (ticket.getId() == ticketId) {
                ticket.displayDetails();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Ticket with ID " + ticketId + " not found.");
        }
    }

    private static void cancelTicket() {
        System.out.print("Enter ticket ID to cancel: ");
        int ticketId = scanner.nextInt();
        boolean canceled = DbManager.deleteTicket(ticketId);
        if (canceled) {
            System.out.println("Ticket canceled successfully.");
        } else {
            System.out.println("Ticket with ID " + ticketId + " not found.");
        }
    }
}
