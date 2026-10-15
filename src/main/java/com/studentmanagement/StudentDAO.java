
package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class StudentDAO {

    // CREATE: Add a student
    public void addStudent(int id, String name,
                           String department, int marks,
                           Integer departmentId) {

        String sql = "INSERT INTO students "
                   + "(ID, NAME, DEPARTMENT, MARKS, department_id) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, department);
            ps.setInt(4, marks);

            if (departmentId == null) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, departmentId);
            }

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    // READ: Display all students
    public void viewStudents() {

        String sql = "SELECT * FROM students";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n========== STUDENT RECORDS ==========");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("ID: " + rs.getInt("ID"));
                System.out.println("Name: " + rs.getString("NAME"));
                System.out.println("Department: " + rs.getString("DEPARTMENT"));
                System.out.println("Marks: " + rs.getInt("MARKS"));
                System.out.println("Department ID: "
                        + rs.getObject("department_id"));
                System.out.println("-------------------------------------");
            }

            if (!found) {
                System.out.println("No student records found.");
            }

        } catch (SQLException e) {
            System.out.println("Read failed: " + e.getMessage());
        }
    }

    // UPDATE: Change a student's marks
    public void updateMarks(int id, int newMarks) {

        if (newMarks < 0 || newMarks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }

        String sql = "UPDATE students SET MARKS = ? WHERE ID = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newMarks);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student marks updated successfully!");
            } else {
                System.out.println("Student ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    // DELETE: Remove a student
    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE ID = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    // MAIN: Test the CRUD operations
    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        // CREATE: Use a new, unused ID to avoid duplicate-key errors.
        // Example:
        // dao.addStudent(105, "Arun", "CSE", 85, 1);

        // READ: Display all existing records.
        dao.viewStudents();

        // UPDATE: Change ID 104's marks to 90.
        // Uncomment to test:
        // dao.updateMarks(104, 90);

        // DELETE: Permanently delete ID 104.
        // Uncomment only if you want to delete this record:
        // dao.deleteStudent(104);
    }
}
