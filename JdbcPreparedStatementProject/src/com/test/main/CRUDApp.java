package com.test.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.test.util.JdbcUtil;

public class CRUDApp {

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Press 1 for Insert operation");
		System.out.println("Press 2 for select operation");
		System.out.println("Press 3 for Update operation");
		System.out.println("Press 4 for Delete operation");
		System.out.println("Press 5 for exit");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet resultSet = null;
		Scanner sc = null;
		
		sc = new Scanner(System.in);
		
		int menu = sc.nextInt();
		
		switch (menu){
		case 1: {
			try {
				
				System.out.println("Provide below asked details to perform insert operation");
				
				conn = JdbcUtil.getJdbConnection();
				
				String sqlInsertQuery = "insert into student(`sname`,`sage`,`saddress`,`gender`) values(?,?,?,?)";
				
				if(conn != null) {
					pstmt = conn.prepareStatement(sqlInsertQuery);
				}
				
				if(pstmt != null) {
					
					System.out.println("Enter student name: ");
					String sname = sc.next();
					
					System.out.println("Enter student age: ");
					int sage = sc.nextInt();
					
					System.out.println("Enter student address: ");
					String saddress = sc.next();
					
					System.out.println("Enter student gender: ");
					String sgender = sc.next();
					
					//precompiled query for setting values
					pstmt.setString(1, sname);
					pstmt.setInt(2, sage);
					pstmt.setString(3, saddress);
					pstmt.setString(4, sgender);
					
					int rowsCount = pstmt.executeUpdate();
					System.out.println("record inserted succesfully "+rowsCount);
				}
			} catch (SQLException e) {
				throw e;
			}
			
		}
		break;
		case 2: {
			
			try {
				System.out.println("Provide student id to get details: ");
				
				String sqlSelectQuery = "select * from student where sid=?";
				
				conn  = JdbcUtil.getJdbConnection();
				
				if(conn != null) {
					 pstmt1 = conn.prepareStatement(sqlSelectQuery);
				}
				if(pstmt1 != null) {
					
					int sid = sc.nextInt();
					
					pstmt1.setInt(1, sid);
					
					resultSet = pstmt1.executeQuery();
					
					System.out.println("SID\tSNAME\tSAGE\tSADDRESS\tSGENDER ");
					
					if(resultSet.next()) {
						System.out.printf("%d%11s%8d%10s%15s",resultSet.getInt(1),resultSet.getString(2),
								resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5));
						System.out.println();
					}else {
						System.out.println("Record not available for given id ");
					}
				}
				
			} catch (Exception e) {
				throw e;
			}
		}
		break;
		case 3: {
			try {
				
				conn = JdbcUtil.getJdbConnection();
				
				String sqlUpdateQuery = "update student set sname=?,sage=?,saddress=? where sid=?";
				String sqlSelectQuery = "select * from student where sid=?";
				
				if(conn != null) {
					 pstmt2 = conn.prepareStatement(sqlUpdateQuery);
					 pstmt3 = conn.prepareStatement(sqlSelectQuery);
					 
				}
				if(pstmt2 != null) {
					
					System.out.println("Enter student id to update ");
					int sid = sc.nextInt();
					
					pstmt3.setInt(1, sid);
					
					resultSet = pstmt3.executeQuery();
					
					System.out.println("Previous Data ");
					if(resultSet.next()) {
						System.out.println("SID\tSNAME\tSAGE\tSADDRESS\tSGENDER");
						
						System.out.printf("%d%11s%8d%10s%15s",resultSet.getInt(1),resultSet.getString(2),
								resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5));
						System.out.println();
					}
					
					System.out.println("Enter new student name: ");
					String sname = sc.next();
					
					System.out.println("Enter new student age: ");
					int sage = sc.nextInt();
					
					System.out.println("Enter new student address: ");
					String saddress = sc.next();
					
					//precompiled query for setting values
					pstmt2.setString(1, sname);
					pstmt2.setInt(2, sage);
					pstmt2.setString(3, saddress);
					pstmt2.setInt(4, sid);
					
					int rowsCount = pstmt2.executeUpdate();
					System.out.println("record updated succesfully "+rowsCount);
				}
			} catch (Exception e) {
				throw e;
			}
		}
		break;
		case 4:{
			try {
				conn = JdbcUtil.getJdbConnection();
				
				String sqlDeleteQuery = "delete from student where sid=?";
				
				if(conn != null) {
					pstmt4 = conn.prepareStatement(sqlDeleteQuery);
				}
				if(pstmt4 != null) {
					
					System.out.println("Enter student id to delete its data ");
					int sid = sc.nextInt();
					
					pstmt4.setInt(1, sid);
					
					int rowCount = pstmt4.executeUpdate();
					System.out.println("Deleted successfully "+rowCount);
				}else {
					System.out.println("Record not available for given id ");
				}
			} catch (Exception e) {
				throw e;
			}
		}
		default:
			System.out.println("Successfully Performed!! ");
		}

	}

}
