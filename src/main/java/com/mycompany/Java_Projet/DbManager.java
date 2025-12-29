package com.mycompany.Java_Projet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbManager {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/miniproj";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public static Connection getConnection() throws SQLException {
        if (PASSWORD.equals("12345678")) {
//            System.out.println("Database connected successfully.");
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } else {
            System.out.println("Incorrect password");
        }
        return null;
    }


    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void createClient(Client client) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "INSERT INTO Client (name, familyname, PassportId, CNI, age) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getName());
            statement.setString(2, client.getFamilyname());
            statement.setString(3, client.getPassportId());
            statement.setString(4, client.getCNI());
            statement.setInt(5, client.getAge());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public static List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "SELECT * FROM Client";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String familyname = resultSet.getString("familyname");
                String passportId = resultSet.getString("PassportId");
                String CNI = resultSet.getString("CNI");
                int age = resultSet.getInt("age");
                Client client = new Client(name, familyname, passportId, CNI, age);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return clients;
    }


    public static void updateClient(Client client) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "UPDATE Client SET name = ?, familyname = ?, CNI = ?, age = ? WHERE PassportId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getName());
            statement.setString(2, client.getFamilyname());
            statement.setString(3, client.getCNI());
            statement.setInt(4, client.getAge());
            statement.setString(5, client.getPassportId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public static void deleteClient(String passportId) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "DELETE FROM Client WHERE PassportId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, passportId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public static boolean deleteVol(String volId) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "DELETE FROM Vol WHERE VolId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, volId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }

    }

    // Update operation for Vol
    public static boolean updateVol(Vol vol) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "UPDATE Vol SET VolNum = ?, from_location = ?, to_location = ?, capacity = ?, dateDepart = ?, dateArrival = ?, price = ?, vol_type = ? WHERE VolId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, vol.getVolNum());
            statement.setString(2, vol.getFrom());
            statement.setString(3, vol.getTo());
            statement.setInt(4, vol.getCapacity());
            statement.setDate(5, new java.sql.Date(vol.getDateDepart().getTime()));
            statement.setDate(6, new java.sql.Date(vol.getDateArrival().getTime()));
            statement.setDouble(7, vol.getPrice());
            statement.setString(8, vol.getVolType());
            statement.setString(9, vol.getVolId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurred
        } finally {
            closeConnection(connection);
        }
    }

    // CRUD operations for Vol

    // Create operation
    public static void createVol(Vol vol) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "INSERT INTO Vol (VolId, VolNum, from_location, to_location, capacity, dateDepart, dateArrival, price, vol_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, vol.getVolId());
            statement.setString(2, vol.getVolNum());
            statement.setString(3, vol.getFrom());
            statement.setString(4, vol.getTo());
            statement.setInt(5, vol.getCapacity());
            statement.setDate(6, new java.sql.Date(vol.getDateDepart().getTime()));
            statement.setDate(7, new java.sql.Date(vol.getDateArrival().getTime()));
            statement.setDouble(8, vol.getPrice());
            statement.setString(9, vol.getVolType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // Read operation
    public static List<Vol> getVols() {
        List<Vol> vols = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "SELECT * FROM Vol";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String volId = resultSet.getString("VolId");
                String volNum = resultSet.getString("VolNum");
                String from = resultSet.getString("from_location");
                String to = resultSet.getString("to_location");
                int capacity = resultSet.getInt("capacity");
                java.util.Date dateDepart = resultSet.getDate("dateDepart");
                java.util.Date dateArrival = resultSet.getDate("dateArrival");
                double price = resultSet.getDouble("price");
                String volType = resultSet.getString("vol_type");
                Vol vol = new Vol(volId, volNum, from, to, capacity, dateDepart, dateArrival, price, volType);
                vols.add(vol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return vols;
    }

    public static void createTicket(Ticket ticket) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "INSERT INTO Ticket (client_passportId, vol_VolId) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, ticket.getClient().getPassportId());
            statement.setString(2, ticket.getVol().getVolId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int ticketId = generatedKeys.getInt(1);
                ticket.setId(ticketId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // Read operation
    public static List<Ticket> getTickets() {
        List<Ticket> tickets = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "SELECT * FROM Ticket";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int ticketId = resultSet.getInt("id");
                String clientPassportId = resultSet.getString("client_passportId");
                String volId = resultSet.getString("vol_VolId");
                // Retrieve corresponding Client and Vol objects
                Client client = getClientByPassportId(clientPassportId);
                Vol vol = getVolById(volId);
                Ticket ticket = new Ticket(client, vol);
                ticket.setId(ticketId);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return tickets;
    }

    // Update operation (not implemented as it may require additional logic)

    // Delete operation
    // Delete operation
    public static boolean deleteTicket(int ticketId) {
        Connection connection = null;
        try {
            connection = getConnection();
            String query = "DELETE FROM Ticket WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ticketId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurred
        } finally {
            closeConnection(connection);
        }
    }


    // Helper methods to retrieve Client and Vol objects

    private static Client getClientByPassportId(String passportId) {
        // Implement logic to retrieve Client object by PassportId from the database
        // This method is just a placeholder, you should implement it according to your database schema
        return null;
    }

    private static Vol getVolById(String volId) {
        // Implement logic to retrieve Vol object by VolId from the database
        // This method is just a placeholder, you should implement it according to your database schema
        return null;
    }
}
