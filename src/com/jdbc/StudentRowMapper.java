package com.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

		System.out.println("mapRow method is called...: " + rowNum);

		Student student = new Student();
		
		student.setSno(rs.getInt("SNO"));
		student.setSname(rs.getString("SNAME"));
		student.setSemail(rs.getString("SEMAIL"));
		student.setSmobile(rs.getString("SMOBILE"));
		
		return student;
	}
}
