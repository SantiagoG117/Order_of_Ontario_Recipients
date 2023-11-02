package DataAccess;


import TransferObjects.Recipients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipientsImplementation implements CRUD {
    //? Attributes
    BuildConnection accessConnection = BuildConnection.getBuildConnection();
    PreparedStatement SQLScript = null;


    //? Methods
    @Override
    public void addInstance(Recipients winner) {
        //* Open MySQL
        Connection connection = accessConnection.connectionBuilder();
        //* Open the SQL script file and write a query on it
        try {
            SQLScript = connection.prepareStatement(
                    "INSERT INTO recipients (Name, Year, City, Category)" +
                            "VALUES (?, ?, ?, ?)"
            );
            //* Store the values
            SQLScript.setString(1, winner.getName());
            SQLScript.setInt(2, winner.getYear());
            SQLScript.setString(3, winner.getCity());
            SQLScript.setString(4, winner.getCategory());
            SQLScript.executeUpdate();

            //* Close connection
            accessConnection.closeConnection();
            SQLScript.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectAllWinners(){
        Connection connection = null;
        Statement SQLscript = null;
        ResultSet resultSet = null;
        try {
            //* Open MySQL and access the connection
            connection = accessConnection.connectionBuilder();
            //* Open a SQL script
            SQLscript = connection.createStatement();
            //* Write the query in the SQL script and store result set
           resultSet = SQLscript.executeQuery("SELECT* FROM recipients");

           //* Print the instances:
           ResultSetMetaData metaData = resultSet.getMetaData();
           int numberOfColumns = metaData.getColumnCount();
           System.out.println("The Order of Ontario Recipients:\n");

            //Print the column names
            for (int i = 1; i <= numberOfColumns; i++)
                //Prints the name of each column using i as the desired column number
                System.out.printf( "%-35s\t", metaData.getColumnName(i));
            System.out.println();

            //Print the records:
            while(resultSet.next()){
                for ( int i = 1; i <= numberOfColumns; i++ )
                    System.out.printf( "%-35s\t", resultSet.getObject(i));
                System.out.println();
            }
            //Print metadata
            System.out.println( "\nRecipients Table - Column Attributes:" );
            for ( int i = 1; i <= numberOfColumns; i++ ) {
                System.out.printf( "%-35s\t", metaData.getColumnName( i ) );
                System.out.printf( "%-35s\t", metaData.getColumnTypeName(i) );
                System.out.printf( "%-35s\t", metaData.getColumnClassName(i) );
                System.out.printf("\n");
            }
            //Close connection
            connection.close();
            SQLscript.close();
            resultSet.close();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    @Override
    public void deleteInstance(Recipients winner) {
        //* Open MySQL
        Connection connection = accessConnection.connectionBuilder();
        try {
            //* Open the SQL script file and write a query on it
            SQLScript = connection.prepareStatement(
                    "DELETE FROM recipients WHERE awardID = ?"
            );
            SQLScript.setInt(1, winner.getRecipientID());
            SQLScript.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Recipients> storeAllInstances() {
        //* Create the ArrayList to store the recipients
        ArrayList<Recipients> recipients = new ArrayList<>();
        try {
            //?Local variables
            //* Open MySQL and access the connection
            Connection connection = accessConnection.connectionBuilder();
            //* Open a SQL script and write the query
            SQLScript = connection.prepareStatement(
                    "SELECT* FROM recipients ORDER BY AwardID"
            );
            //* Execute the query and store the results in a ResultSet
            ResultSet resultSet = SQLScript.executeQuery();

            //* Add recipients to the ArrayList
            while (resultSet.next()) {
                // Create recipient and set its values
                Recipients recipient = new Recipients();
                recipient.setRecipientID(resultSet.getInt("AwardID"));
                recipient.setCategory(resultSet.getString("Category"));
                recipient.setCity(resultSet.getString("City"));
                recipient.setName(resultSet.getString("Name"));
                recipient.setYear(resultSet.getInt("Year"));
                // Add recipient to the ArrayList
                recipients.add(recipient);
            }
            //*close connection
            resultSet.close();
            SQLScript.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return recipients;
    }

    @Override
    public Recipients getInstanceByID(int winnerID) {
        return null;
    }

    @Override
    public void updateInstance(Recipients winner) {

    }






}
