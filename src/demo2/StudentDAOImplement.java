package demo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImplement implements StudentDAO {

	private final static String queryString = "jdbc:mysql://localhost:3306/{SCHEMA-NAME}?user={USER-NAME}&password={PASSWORD}";

	public Student getStudentById(int id) {
		Connection connection = null;
		Student student = null;
		try {
			connection = DriverManager.getConnection(queryString);
			String query = "select * from studenttable where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				int s_id = set.getInt("id");
				String name = set.getString("name");
				int age = set.getInt("age");
				String phoneNumber = set.getString("phoneNumber");
				String email = set.getString("email");
				student = new Student(id, name, age, phoneNumber, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return student;
	}

	public boolean insertStudent(Student s) {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DriverManager.getConnection(queryString);
			String query = "insert into studenttable(id, name, age, phoneNumber, email) values(?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, s.getId());
			statement.setString(2, s.getName());
			statement.setInt(3, s.getAge());
			statement.setString(4, s.getPhoneNumber());
			statement.setString(5, s.getEmail());
			statement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean updateStudent(int studentId, Student updatedStudent) {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DriverManager.getConnection(queryString);
			String query = "update studenttable set id=?, name=?, age=?, phoneNumber=?, email=? where id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, updatedStudent.getId());
			statement.setString(2, updatedStudent.getName());
			statement.setInt(3, updatedStudent.getAge());
			statement.setString(4, updatedStudent.getPhoneNumber());
			statement.setString(5, updatedStudent.getEmail());
			statement.setInt(6, studentId);
			int rowsEffected = statement.executeUpdate();
			if (rowsEffected > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Try Again. There is an error.");
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean deleteStudent(int id) {
		Connection connection = null;
		Student student = null;
		boolean flag = false;
		try {
			connection = DriverManager.getConnection(queryString);
			String query = "Delete from studenttable where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public void fetchStudents() throws InterruptedException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(queryString);
			Statement statement = connection.createStatement();
			String query = "Select * from studenttable";
			ResultSet set = statement.executeQuery(query);
			System.out.println("DATA FETCHING....");
			while (set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				int age = set.getInt(3);
				String phoneNumber = set.getString(4);
				String email = set.getString(5);
				Thread.sleep(2000);
				System.out.println();
				System.out.println("ID : " + id);
				System.out.println("NAME : " + name);
				System.out.println("AGE : " + age);
				System.out.println("Phone : " + phoneNumber);
				System.out.println("Email : " + email);
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}