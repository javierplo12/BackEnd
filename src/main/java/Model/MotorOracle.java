package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class MotorOracle {
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;
    private static final String URL  = "jdbc:oracle:thin:@burgertown.cxslw6iucvub.us-east-1.rds.amazonaws.com:1521:orcl";
    private static final String USER  = "admin";
    private static final String PASSWORD  = "123456789";

    public void connect() {
        try {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            st = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public int execute(String sql) {
        int resp = 0;
        try {
            if (st != null) {
                resp = st.executeUpdate(sql);
            } else {
                System.out.println("Statement is null. Cannot execute query.");
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return resp;
    }

    public ResultSet executeQuery(String sql) {
        try {
            if (st != null) {
                rs = st.executeQuery(sql);
            } else {
                System.out.println("Statement is null. Cannot execute query.");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return rs;
    }

    public int executeUpdate(String sql) {
        int iResults = 0;
        try {
            if (st != null) {
                iResults = st.executeUpdate(sql);
            } else {
                System.out.println("Statement is null. Cannot execute query.");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return iResults;
    }

    public void disconnect() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) {
                System.out.println("SQLException on closing ResultSet: " + sqlEx.getMessage());
            }
            rs = null;
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException sqlEx) {
                System.out.println("SQLException on closing Statement: " + sqlEx.getMessage());
            }
            st = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("SQLException on closing Connection: " + e.getMessage());
            }
            conn = null;
        }
    }
}
