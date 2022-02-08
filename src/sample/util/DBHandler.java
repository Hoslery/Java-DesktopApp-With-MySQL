package sample.util;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DBHandler extends Configs{
    private static Connection dbConnection = null;
    private static final String connectionString = "jdbc:mysql://" + dbHost + ":"
            + dbPort + "/" + dbName;

    public static void  getDbConnection()
            throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Где ваш драйвер JDBC?");
            e.printStackTrace();
            throw e;
        }

        try {
            dbConnection = DriverManager.getConnection(connectionString,
                    dbUser, dbPassword);
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение! Проверьте консоль вывода" + e);
            e.printStackTrace();
            throw e;
        }

    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }

    public static void useStmt(String statement) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            getDbConnection();
            stmt = dbConnection.createStatement();
            stmt.executeUpdate(statement);
        } catch (SQLException e) {
            System.out.println("Database access error occurs or this method is called on a closed connection: " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }

    public static ResultSet dbExecuteQuery(String statement) throws SQLException, ClassNotFoundException{

        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {

            getDbConnection();

            stmt = dbConnection.createStatement();
            resultSet = stmt.executeQuery(statement);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }
}
