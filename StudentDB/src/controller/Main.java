package controller;

import java.util.List;
import javadb.StudentDAO;
import javadb.DBaccess;
import model.Student;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
public class Main {

	private DBaccess db;
	private StudentDAO dao;

	public Main() {
		super();
		db = new DBaccess();
		dao = new StudentDAO(db);
	}

	public static void main(String[] args) {
		Main myself = new Main();
		myself.run();
	}

	private void run() {
	
		try {
			db.openConnection();
			System.out.println("Connection open");
			dao.createTable();
		} 
		catch (Exception e) {
			System.out.println("\nSomething went wrong\n");
			e.printStackTrace();
		}
		
//		Create a student and save it in the database.
		Student theo = new Student(123456, "Theo", "Teaman", "", "Earl Grey Street 123", "1234AB", "Teatown");
		dao.storeStudent(theo);
		
//		Fetch all students out of the database from the city of Teatown and print these.
		List<Student> studCity = dao.getStudentByCity("Teatown");
		for (Student s : studCity) {
			System.out.println(s);
		}
		
		db.closeConnection();

	}
}
