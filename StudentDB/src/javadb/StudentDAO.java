package javadb;

import java.sql.*;
import java.util.*;
import model.Student;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
public class StudentDAO {
	
	private DBaccess db;
	private String TableName = "Student";

	public StudentDAO(DBaccess db) {
		super();
		this.db = db;
	}
	
	public String getTableName() {
		return TableName;
	}

	public void createTable() throws SQLException {
	    String sqlCreate = "CREATE TABLE IF NOT EXISTS " + this.getTableName()
	            + "  (studentnr       INTEGER,"
	            + "   s_voornaam      VARCHAR(50),"
	            + "   s_tussenvoegsel VARCHAR(50),"
	            + "   s_achternaam    VARCHAR(50),"
	            + "   adres		      VARCHAR(50),"
	            + "   postcode	      VARCHAR(50),"
	            + "   woonplaats	  VARCHAR(20))";
	    try {
	    	PreparedStatement ps = db.getStatement(sqlCreate);
	    	db.executeUpdatePreparedStatement(ps);
	    } catch (SQLException e) {
	    	System.out.println("SQL error " + e.getMessage());
	    }
	}
	
	public void storeStudent(Student student) {
		String sql = "insert into Student(studentnr, s_voornaam, s_tussenvoegsel, s_achternaam"
				+ ", adres, postcode, woonplaats) values(?,?,?,?,?,?,?);";
		try {
			PreparedStatement ps = db.getStatement(sql);
			ps.setInt(1, student.getStudentId());
			ps.setString(2, student.getFirstName());
			ps.setString(3, student.getAffix());
			ps.setString(4, student.getLastName());	
			ps.setString(5, student.getAddress());	
			ps.setString(6, student.getPostalCode());
			ps.setString(7, student.getCity());
			db.executeUpdatePreparedStatement(ps);
		} catch (SQLException e) {
			System.out.println("SQL error " + e.getMessage());
		}
  	}
	
	public List<Student> getStudentByCity(String woonplaats) {
		String sql = "Select * from Student where woonplaats = ?;";
		
		List<Student> result = new ArrayList<Student>();
		try {
			PreparedStatement ps = db.getStatement(sql);
			ps.setString(1, woonplaats);
			ResultSet rs = db.executeSelectPreparedStatement(ps);
			
			while (rs.next()) {
				int studentId = rs.getInt("studentnr");
				String firstName = rs.getString("s_voornaam");
				String affix = rs.getString("s_tussenvoegsel");
				String lastName = rs.getString("s_achternaam");
				String address = rs.getString("adres");
				String postalCode = rs.getString("postcode");
				String city = rs.getString("woonplaats");
				Student resultElement = new Student(studentId, firstName
					, affix, lastName, address, postalCode, city);
				result.add(resultElement);
			}
		} catch (SQLException e) {
			System.out.println("SQL error " + e.getMessage());
			}
			return result;
	}
	
}
