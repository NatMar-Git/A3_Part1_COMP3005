import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class AppFunctions {
    public static void getAllStudents(Connection connection) throws SQLException { //Print out all students info
        String SQL = "SELECT * FROM students";
        PreparedStatement pstmt = connection.prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println("Student ID: " + rs.getInt("student_id"));
            System.out.println("First Name: " + rs.getString("first_name"));
            System.out.println("Last Name: " + rs.getString("last_name"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Enrollment Date: " + rs.getDate("enrollment_date"));
            System.out.println();
        }
        rs.close();
        pstmt.close();

    }

    //Add a student with given data
    public static void addStudent(Connection connection, String firstName, String lastName, String email, Date enrollmentDate) throws SQLException {
        String insertSQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setDate(4, enrollmentDate);
            pstmt.executeUpdate();
            System.out.println("Student Added successfully.");
            System.out.println();
        }

    }

    // Update the students email, identified with studentID
    public static void updateStudentEmail(Connection connection, int studentId, String newEmail) throws SQLException {
        String updateSQL = "UPDATE students set email = ? WHERE student_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(updateSQL);

        pstmt.setString(1, newEmail);
        pstmt.setInt(2, studentId);

        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Student email has been updated");
        }
        else {
            System.out.println("Student not found");
        }
        pstmt.close();
    }

    //Delete student from table using their student id
    public static void deleteStudent(Connection connection, int studentId) throws SQLException {
        String insertSQL = "DELETE FROM students WHERE student_id = ?";
        PreparedStatement statement = connection.prepareStatement(insertSQL);

        statement.setInt(1, studentId);

        int rowsDeleted = statement.executeUpdate();
        statement.close();

        if (rowsDeleted > 0) {
            System.out.println("Student has been deleted");
        }
        else {
            System.out.println("Student not found");
        }
    }
}
