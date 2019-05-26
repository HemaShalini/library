import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCExample {


    private JDBCExample()
    {

    }

    public static JDBCExample getInstance()
    {
        return new JDBCExample();
    }

    public Connection getConnection() {

  //      System.out.println("-------- PostgreSQL "
    //            + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();

        }

        //System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/LibraryManagementSystem", "postgres",
                    "12345");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();


        }

        if (connection != null) {
        } else {
            System.out.println("Failed to make connection!");
        }

        return connection;
    }
    }
