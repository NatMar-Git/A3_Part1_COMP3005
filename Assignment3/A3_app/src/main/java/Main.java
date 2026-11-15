import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/A3";
        String user = "postgres";
        String password = "admin";

        Scanner scanner = new Scanner(System.in); // Have a scanner to read user input

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            if(connection != null){
                System.out.println("Connected to the database");

                int choice = 0;

                //Display options and keep running until user exits
                while (choice != 5) {

                    System.out.println("\nSTUDENT DATABASE");
                    System.out.println("1. View all students");
                    System.out.println("2. Add a student");
                    System.out.println("3. Update student email");
                    System.out.println("4. Delete a student");
                    System.out.println("5. Exit");
                    System.out.print("Enter your choice: ");

                    try {
                        choice = Integer.parseInt(scanner.nextLine());

                        switch (choice) {

                            case 1:
                                // Display all students
                                AppFunctions.getAllStudents(connection);
                                break;

                            case 2:
                                // Query user for student info
                                System.out.print("First name: ");
                                String firstName = scanner.nextLine();
                                System.out.print("Last name: ");
                                String lastName = scanner.nextLine();
                                System.out.print("Email: ");
                                String email = scanner.nextLine();
                                System.out.print("Enrollment date (YYYY-MM-DD): ");
                                Date enrollmentDate = Date.valueOf(scanner.nextLine());

                                AppFunctions.addStudent(connection, firstName, lastName, email, enrollmentDate);
                                break;

                            case 3:
                                // Collect student ID and new email
                                System.out.print("Enter student ID: ");
                                int updateId = Integer.parseInt(scanner.nextLine());

                                System.out.print("Enter new email: ");
                                String newEmail = scanner.nextLine();

                                AppFunctions.updateStudentEmail(connection,
                                        updateId, newEmail);
                                break;

                            case 4:
                                // Query user for student id to delete
                                System.out.print("Enter student ID: ");
                                int deleteId = Integer.parseInt(scanner.nextLine());

                                AppFunctions.deleteStudent(connection, deleteId);
                                break;

                            case 5:
                                System.out.println("Exiting application");
                                break;

                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
                    }

                    //If the user attempts an invalid input stops them, e.g. inputting a non date in the date variable
                    catch (IllegalArgumentException e) {
                        System.out.println("Invalid input. Please check your entries.");
                    }
                }
            }
            else{
                System.out.println("Failed to connect to the database");
            }
        }

        catch(Exception e){
            System.out.println(e);
        }
    }
}
