package demo2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws IOException, InterruptedException, SQLException {
		StudentDAO s = new StudentDAOImplement();
//		Student student = new Student();
		Scanner sc = new Scanner(System.in);
        
		while (true) {
        	
        	System.out.println("----------- Welcome to Student Management System -----------");
    		System.out.println();
    		System.out.println("PRESS 1 to 'ADD' DETAILS. ");
    		System.out.println("Press 2 to 'DELETE' DETAILS. ");
    		System.out.println("Press 3 to 'UPDATE' a given student.");
            System.out.println("Press 4 to 'DISPLAY' a given student.");
            System.out.println("Press 5 to EXIT");
            System.out.println();
            
            int choice = sc.nextInt();

            
			switch (choice) {
			case 1:
				System.out.println("Enter ID number : ");
				int studentId = sc.nextInt();
				System.out.println("Enter Student Name : ");
				String studentName = sc.next();
				System.out.println("Enter Student Age : ");
				int studentAge = sc.nextInt();
				System.out.println("Enter Student Phone Number : ");
				String studentPhNo = sc.next();
				System.out.println("Enter Student Email : ");
				String email = sc.next();
				Student student = new Student(studentId, studentName, studentAge, studentPhNo, email);
				if (s.insertStudent(student)) {
					System.out.println();
					System.out.println("Student added successfully...");
					Thread.sleep(2000);
					System.out.println();
				} else {
					System.out.println("Something went wrong. Please Try Again");
				}
				System.out.println();
				break;
			case 2:
				System.out.println("Enter the 'ID' of the student you would want to delete.");
				int studentID = sc.nextInt();
				if (s.deleteStudent(studentID)) {
					System.out.println("Deleting...");
					Thread.sleep(1000);
					System.out.println("Student deleted successfully!!");
				} else {
					System.out.println("Something went wrong. Try Again");
				}
				break;
			case 3:
				System.out.println("Enter the 'ID' of the student you would want to update");
				int studentID1 = sc.nextInt();
				Student existingStudent = s.getStudentById(studentID1);
				if (existingStudent != null) {
					sc.nextLine();
					
					System.out.println("Enter the updated name of the Student (or press Enter to skip): ");
		            String updatedName = sc.nextLine().trim();
		            if (updatedName.isEmpty()) updatedName = existingStudent.getName();

		            System.out.println("Enter the updated age of the Student (or press Enter to skip): ");
		            String inputAge = sc.nextLine().trim();
		            int updatedAge = inputAge.isEmpty() ? existingStudent.getAge() : Integer.parseInt(inputAge);

		            System.out.println("Enter the updated phone number of the Student (or press Enter to skip): ");
		            String updatedPhone = sc.nextLine().trim();
		            if (updatedPhone.isEmpty()) updatedPhone = existingStudent.getPhoneNumber();

		            System.out.println("Enter the updated email of the Student (or press Enter to skip): ");
		            String updatedEmail = sc.nextLine().trim();
		            if (updatedEmail.isEmpty()) updatedEmail = existingStudent.getEmail();

		            Student updatedStudent = new Student(studentID1, updatedName, updatedAge, updatedPhone, updatedEmail);
					if (s.updateStudent(studentID1, updatedStudent)) {
						System.out.println("Updating...");
					} else {
						System.out.println("Something went wrong, Please try again");
					}
				} else {
					System.out.println("No Student found with ID : " + studentID1);
				}
				break;
			case 4:
				s.fetchStudents();
				break;
			case 5:
				System.out.println("Exiting....");
				Thread.sleep(500);
				System.out.println("Good bye");
				sc.close();
				return;
				
				
			default:
				System.out.println("Invalid Option..");

			}
		
      }
       
	}
}
