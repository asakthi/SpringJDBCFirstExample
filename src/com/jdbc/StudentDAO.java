package com.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;*/
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.PreparedStatementSetter;

public class StudentDAO {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/*
	 * public void insertStudent(Student student) {
	 * 
	 * String insertQuery =
	 * "insert into STUDENTDETAILS (SNO, SNAME, SEMAIL, SMOBILE) values (?, ?, ?, ?)"
	 * ; jdbcTemplate.update(insertQuery, student.getSno(),student.getSname(),
	 * student.getSemail(),student.getSmobile());
	 * 
	 * }
	 */

	public void insertStudent(final Student student) {

		String insertQuery = "insert into STUDENTDETAILS (SNO, SNAME, SEMAIL, SMOBILE) values (?, ?, ?, ?)";

		int noOfRowsInserted = jdbcTemplate.update(insertQuery, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				System.out.println("setValues method called..");
				ps.setInt(1, student.getSno());
				ps.setString(2, student.getSname());
				ps.setString(3, student.getSemail());
				ps.setString(4, student.getSmobile());
			}
		});

		System.out.println("noOfRowsInserted: " + noOfRowsInserted);
	}

	public void updateStudent(Student student) {

		String updateQuery = "UPDATE STUDENTDETAILS SET SEMAIL=?, SMOBILE=? WHERE SNO=?";
		jdbcTemplate.update(updateQuery, student.getSemail(), student.getSmobile(), student.getSno());

	}

	public List<Student> getStudent() {
		String sql = "select * from studentDetails";
		List<Student> list = jdbcTemplate.query(sql, new StudentRowMapper());
		return list;
	}


	public List<Student> getStudentWithWhereClause() {
		String sql = "select * from studentDetails where sno>?";
		List<Student> list = jdbcTemplate.query(sql, new Object[] { 2 }, new StudentRowMapper());
		return list;
	}

	public List<String> getStudentMobileNos() {

		String sql = "select smobile from studentdetails ";
		List<String> list = jdbcTemplate.queryForList(sql, String.class);
		return list;
	}
	
	public int getStudentsCount() {
		String sql = "select count(*) from studentdetails";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

}
