package ru.stqa.pft.m2.tests;

import org.testng.annotations.Test;

import java.sql.*;


public class DBConnectionTest {

    @Test
    public void DBConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://mysql56.sty/shevtsova_mg210ce40?" +
                            "user=shevtsova&password=thohkeuv");
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("select * from `customer_entity`");
            while (result.next()){
                System.out.println(
                        result.getInt("entity_id")+ " " +
                        result.getString("email")+ " " +
                        result.getString("firstname")+ " " +
                        result.getString("lastname")+ " "
                );
            }
            result.close();
            st.close();
            conn.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
