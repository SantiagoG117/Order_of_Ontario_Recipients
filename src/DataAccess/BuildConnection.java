package DataAccess;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ? Features of Singleton:
 *  ? Private constructor
 *  ? private static variable holding the instance
 *  ? Getter to access the instance from outside
 *
 */

public class BuildConnection {
    //?Attributes
    //* Manages connection (Open MySQL)
    private Connection connection = null;
    //* Creates a SQL file: Create a statement for querying the database
    private PreparedStatement SQLstatement = null;
    //* Help us work with the results
    ResultSet resultSet = null;
    /*
        * We use properties to not hardcode the details of our connection
        * Stores the values in the database.properties:
            * jdbc.url
            * jdbc.username
            * jdbc.password
     */
    private Properties properties = new Properties();

    /*
        * Reading the database properties file and storing the content in the input object
     */
    private InputStream input;


    private static BuildConnection buildConnection = new BuildConnection();


    // ? Constructor
    private BuildConnection() {}

    //? Establishing the connection
    //?  Details:
    public Connection connectionBuilder(){
        //* CreateDetails
        try (InputStream input = Files.newInputStream(Paths.get("src/DataAccess/database.properties"))){
            properties.load(input);
        } catch (IOException e){
            e.printStackTrace();
        }

        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");


        //*BuildConnection: Open MySQL and create the connection
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //? Private methods:
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //? Getters
    public static BuildConnection getBuildConnection() {
        return buildConnection;
    }

}
