package com.ems.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ems.DatabaseConnection;
import com.ems.exception.*;

public class GuestDao {
	Scanner sc = new Scanner(System.in);
	// method to fetch department details
			public void getDepartmentById() throws ResourceNotFoundException {
				try (Connection con = DatabaseConnection.createConnection()) {
					Statement st = con.createStatement();

					System.out.println("Enter department id: ");
					String deptId = sc.next();

					String query = "select dept_id, dept_name, no_of_employee from" + " department where dept_id='"
							+ deptId + "'";

					ResultSet rs = st.executeQuery(query);

					if (rs.next()) {
						System.out.println("Department Id: " + rs.getString("dept_id"));
						System.out.println("Department Name: " + rs.getString("dept_name"));
						System.out.println("Total number of employees: " + rs.getString("no_of_employee"));
						System.out.println();
		                System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		                System.out.println();
					} else {
						throw new ResourceNotFoundException("Department id - " + deptId + " is not found !");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InputMismatchException e) {
					e.printStackTrace();
				}
			}
			// Method to fetch employee details by ID
		    public void getEmployeeById() throws ResourceNotFoundException {
		        try (Connection con = DatabaseConnection.createConnection()) {
		            Statement st = con.createStatement();

		            System.out.println("Enter employee ID: ");
		            int empId = sc.nextInt();

		            String query = "SELECT emp_id, emp_name, gender, designation, salary, email, phone_no, joining_date, role_id, password " +
		                           "FROM employee WHERE emp_id = " + empId;

		            ResultSet rs = st.executeQuery(query);
		            if (rs.next()) {
		                System.out.println("Employee ID: " + rs.getInt("emp_id"));
		                System.out.println("Employee Name: " + rs.getString("emp_name"));
		                System.out.println("Gender: " + rs.getString("gender"));
		                System.out.println("Designation: " + rs.getString("designation"));
		                System.out.println("Salary: " + rs.getDouble("salary"));
		                System.out.println("Email: " + rs.getString("email"));
		                System.out.println("Phone Number: " + rs.getString("phone_no"));
		                System.out.println("Joining Date: " + rs.getString("joining_date"));
		                System.out.println("Role ID: " + rs.getInt("role_id"));
		                System.out.println("Password: " + rs.getString("password"));
		                System.out.println();
		                System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		                System.out.println();
		            } else {
		                throw new ResourceNotFoundException("Employee ID - " + empId + " is not found !");
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (InputMismatchException e) {
		            e.printStackTrace();
		        }
		    }
		 // Method to fetch project details by ID
		    public void getProjectById() throws ResourceNotFoundException {
		        try (Connection con = DatabaseConnection.createConnection()) {
		            Statement st = con.createStatement();

		            System.out.println("Enter project ID: ");
		            int projectId = sc.nextInt();

		            String query = "SELECT project_id, project_name, designation, start_date, end_date, status " +
		                           "FROM project WHERE project_id = " + projectId;

		            ResultSet rs = st.executeQuery(query);
		            if (rs.next()) {
		                System.out.println("Project ID: " + rs.getInt("project_id"));
		                System.out.println("Project Name: " + rs.getString("project_name"));
		                System.out.println("Description: " + rs.getString("description"));
		                System.out.println("Start Date: " + rs.getString("start_date"));
		                System.out.println("End Date: " + rs.getString("end_date"));
		                System.out.println("Status: " + rs.getString("status"));
		                System.out.println();
		                System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		                System.out.println();
		            } else {
		                throw new ResourceNotFoundException("Project ID - " + projectId + " is not found !");
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (InputMismatchException e) {
		            e.printStackTrace();
		        }
		    }

}
