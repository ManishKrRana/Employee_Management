package com.ems.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ems.DatabaseConnection;

public class DepartmentDao {
	Scanner sc = new Scanner(System.in);
	public void addDepartment()
	{
		try(Connection con = DatabaseConnection.createConnection())
		{
			Statement st = con.createStatement();
			
			System.out.println("Enter department name: ");
			String deptName = sc.nextLine();
			
			System.out.println("Enter total no. of employee: ");
			int noOfEmployee = sc.nextInt();
			
			//logic to add custom department id
			String fetchId = "select dept_id from department order by dept_id desc limit 1";
		 	ResultSet rs = st.executeQuery(fetchId);
		 	
		 	if(rs.next())
		 	{
		 		String lastDeptId = rs.getString("dept_id");
		 		String prefix = lastDeptId.substring(0,1);
		 		String postfix= lastDeptId.substring(1);
		 		int deptId = Integer.parseInt(postfix);  //Converting a string into integer
		 		String newDeptId = prefix+(deptId+1);
		 		
		 		String insert = "insert into department value ('"+newDeptId+"','"+deptName+"',"
		 				+ "'"+noOfEmployee+"')";
		 		
		 		int row	= st.executeUpdate(insert);
		 		System.out.println(row+"Added successfully...");
		 	}	
		 	else {
		 		String lastDeptId = "D0";
		 		String prefix = lastDeptId.substring(0,1);
		 		String postfix= lastDeptId.substring(1);
		 		int deptId = Integer.parseInt(postfix);
		 		String newDeptId = prefix+(deptId+1);
		 		
		 		String insert = "insert into department value ('"+newDeptId+"','"+deptName+"',"
		 				+ "'"+noOfEmployee+"')";
		 		
		 		int row	= st.executeUpdate(insert);
		 		System.out.println(row+"Added successfully...");

		 	}
	
		}
		catch (SQLException e) {
				e.printStackTrace();
		}
		catch(InputMismatchException e ) {
			e.printStackTrace();
		}
		
	}
	//method to fetch department details
		public void getAllDepartment() {
			try (Connection con = DatabaseConnection.createConnection()) {
				Statement st = con.createStatement();

				String query = "select * from department";

				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					System.out.println("Department Id: " + rs.getString("dept_id"));
					System.out.println("Department Name: " + rs.getString("dept_name"));
					System.out.println("Total number of employee: " + rs.getString("no_of_employee"));
					System.out.println();
	                System.out.println("++++++++++++++++++++++++++++++++++++++++++");
	                System.out.println();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch(InputMismatchException e ) {
				e.printStackTrace();
			}
		}
		//method to modify department details using department id
		public void modifyDepartment() {
			try (Connection con = DatabaseConnection.createConnection()) {
				Statement st = con.createStatement();

				System.out.println("Enter department id to update: ");
				String deptId = sc.next();
				
				sc.nextLine();
				System.out.println("Enter department name: ");
				String deptName = sc.nextLine();

				System.out.println("Enter total number of employee: ");
				int noOfEmpl = sc.nextInt();

				String update = "update department set dept_name= '" + deptName + "'," + "no_of_employee= '"
						+ noOfEmpl + "'" + "where dept_id= '" + deptId + "'";

				int row = st.executeUpdate(update);
				System.out.println(row + " Updated successfully...");
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch(InputMismatchException e ) {
				e.printStackTrace();
			}
		}
}
