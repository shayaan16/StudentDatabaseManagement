
package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionCommitDemo {

    public static void main(String[] args) {

        String sql = "UPDATE students SET MARKS = ? WHERE ID = ?";

        try (Connection con = DatabaseConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, 92);
                ps.setInt(2, 104);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    con.commit();
                    System.out.println(
                        "Transaction committed. Marks saved successfully!"
                    );
                } else {
                    con.rollback();
                    System.out.println("Student ID 104 not found.");
                }

            } catch (SQLException e) {
                con.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }
    }
}
