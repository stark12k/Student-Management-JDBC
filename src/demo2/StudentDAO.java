package demo2;

public interface StudentDAO {
	public boolean insertStudent(Student s);
	public boolean updateStudent(int id, Student updatedStudent);
	public boolean deleteStudent(int id);
	public void fetchStudents() throws InterruptedException;
	public Student getStudentById(int id);
}
