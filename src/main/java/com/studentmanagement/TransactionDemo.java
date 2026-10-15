
package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDemo {

    public static void main(String[] args) {

        String sql = "UPDATE students SET MARKS = ? WHERE ID = ?";

        try (Connection con = DatabaseConnection.getConnection()) {

            // Disable automatic commits
            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, 95);
                ps.setInt(2, 104);

                int rows = ps.executeUpdate();

                if (rows == 0) {
                    System.out.println("Student ID 104 not found.");
                    con.rollback();
                    return;
                }

                // Demonstrate rollback: discard the update
                con.rollback();

                System.out.println(
                    "Transaction rolled back. Original marks are preserved."
                );
            }

        } catch (SQLException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }
    }
}
