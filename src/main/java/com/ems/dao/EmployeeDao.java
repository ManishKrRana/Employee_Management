package com.ems.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ems.DatabaseConnection;

public class EmployeeDao {
	Scanner sc = new Scanner(System.in);

    public void addEmployee() {
        try (Connection con = DatabaseConnection.createConnection()) {
            Statement st = con.createStatement();

            System.out.println("Enter Name: ");
            String empName = sc.nextLine();
            
            System.out.println("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.println("Enter Designation: ");
            String designation = sc.nextLine();

            System.out.println("Enter employee salary: ");
            double salary = sc.nextDouble();
            sc.nextLine(); 

            System.out.println("Enter Email: ");
            String email = sc.nextLine();

            System.out.println("Enter Phone number: ");
            String phoneNo = sc.nextLine();

            System.out.println("Enter joining date (yyyy-MM-dd): ");
            String joiningDate = sc.nextLine();

            System.out.println("Enter role ID: ");
            int roleId = sc.nextInt();

            sc.nextLine(); // Consume the newline character

            System.out.println("Enter Password: ");
            String password = sc.nextLine();

            String insert = "INSERT INTO employee (emp_name, designation, salary, email, phone_no, joining_date, role_id, password) " +
                            "VALUES ('" + empName + "','" + gender + "', '" + designation + "', " + salary + ", '" + email + "', '" + phoneNo + "', '" + joiningDate + "', " + roleId + ", '" + password + "')";

            int row = st.executeUpdate(insert);
            System.out.println(row + " Added successfully!!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }

    public void modifyEmployee() {
        try (Connection con = DatabaseConnection.createConnection()) {
            Statement st = con.createStatement();

            System.out.println("Enter employee ID to update: ");
            int empId = sc.nextInt();

            sc.nextLine(); // Consume the newline character

            System.out.println("Enter Name: ");
            String empName = sc.nextLine();
            
            System.out.println("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.println("Enter Designation: ");
            String designation = sc.nextLine();

            System.out.println("Enter Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            System.out.println("Enter Email: ");
            String email = sc.nextLine();

            System.out.println("Enter Phone number: ");
            String phoneNo = sc.nextLine();

            System.out.println("Enter joining date: ");
            String joiningDate = sc.nextLine();

            System.out.println("Enter role ID: ");
            int roleId = sc.nextInt();
            sc.nextLine(); 

            System.out.println("Enter password: ");
            String password = sc.nextLine();

            String update = "UPDATE employee " +
                    "SET emp_name = '" + empName + "', " +
                    "gender = " + gender + ", " +
                    "designation = '" + designation + "', " +
                    "salary = " + salary + ", " +
                    "email = '" + email + "', " +
                    "phone_no = '" + phoneNo + "', " +
                    "joining_date = '" + joiningDate + "', " +
                    "role_id = " + roleId + ", " +
                    "password = '" + password + "' " +
                    "WHERE emp_id = " + empId;

            int row = st.executeUpdate(update);
            System.out.println(row + " Updated successfully!!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }

    public void viewEmployees() {
        try (Connection con = DatabaseConnection.createConnection()) {
            Statement st = con.createStatement();

            String query = "SELECT * FROM employee";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }
}
