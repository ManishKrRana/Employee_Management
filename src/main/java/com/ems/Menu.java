package com.ems;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ems.dao.DepartmentDao;
import com.ems.dao.EmployeeDao;
import com.ems.dao.GuestDao;
import com.ems.dao.ProjectDao;
import com.ems.exception.ResourceNotFoundException;

public class Menu {
	public static String roleName;
	static Scanner sc = new Scanner(System.in);
	int choice;
	EmployeeDao employeeDao = new EmployeeDao();
	DepartmentDao departmentDao = new DepartmentDao();
	GuestDao guestDao = new GuestDao();
	ProjectDao projectDao = new ProjectDao();

	public void login() {
		try (Connection con = DatabaseConnection.createConnection()) {
			Statement st = con.createStatement();

			System.out.println("Login");

			System.out.println("Enter email: ");
			String email = sc.next();

			System.out.println("Enter password: ");
			String password = sc.next();

			String fetchQuery = "select role_id from employee where email='" + email + "' " + "and password='"
					+ password + "'";

			ResultSet rs = st.executeQuery(fetchQuery);

			if (rs.next()) {
				int role_id = rs.getInt("role_id");

				String getRole = "select role_name from role where role_id= " + role_id + "";

				ResultSet rs1 = st.executeQuery(getRole);
				while (rs1.next()) {
					roleName = rs1.getString("role_name");
				}
			} else {
				System.out.println("Ohhhooo!!! Please check the email and password.");
				login();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}

	}

	// Main menu
	public void mainMenu() {
		try {
			if (roleName.equals("Admin")) {
				adminMenu();
			} else if (roleName.equals("Guest")) {
				guestMenu();
			}
		} catch (NullPointerException e) {
			System.out.println("Not Found !");
		}
		sc.next();
	}

	// Admin menu
	public void adminMenu() {
		do {
			System.out.println();
			System.out.println("Admin Menu");
			System.out.println();
			System.out.println("------------------------------------");
			System.out.println();
			System.out.println("1.View All Department\r\n" + "2.Add Department\r\n" + "3. Update Department\r\n"
					+ "4. View All Employee\r\n" + "5. Add Employee\r\n" + "6. Modify Employee\r\n"
					+ "7. View All Project\r\n" + "8. Add Project\r\n" + "9.Modify Project\r\n" + "10.Exit");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("View All Department: ");
				departmentDao.getAllDepartment();
				break;

			case 2:
				System.out.println("Add Department : ");
				departmentDao.addDepartment();
				break;

			case 3:
				System.out.println("Update Department : ");
				departmentDao.modifyDepartment();
				break;

			case 4:
				System.out.println("View All Employee : ");
				break;

			case 5:
				System.out.println("Add Employee : ");
				employeeDao.addEmployee();
				break;

			case 6:
				System.out.println("Update Employee : ");
				employeeDao.modifyEmployee();
				break;

			case 7:
				System.out.println("View All Project : ");
				projectDao.viewProjects();
				break;

			case 8:
				System.out.println("Add Project : ");
				projectDao.addProject();
				break;

			case 9:
				System.out.println("Update project : ");
				projectDao.modifyProject();
				break;

			case 10:
				System.out.println("Exit");
				System.out.println("Exit");
				System.exit(0);
				break;
			default:
				System.out.println("Ohhhooo!!! Please enter correct input.");
				break;
			}
		} while (choice != 10);

	}

	public void guestMenu() {
		do {
			System.out.println("Guest Menu");
			System.out.println();
			System.out.println("------------------------------------");
			System.out.println();
			System.out.println("1. View Departments\r\n" + "2. View Employee\r\n" + "3. View Project\r\n" + "4. Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("View Departments :");
				try {
					guestDao.getDepartmentById();
				} catch (ResourceNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 2:
				System.out.println("View Employee :");
				try {
					guestDao.getEmployeeById();
				} catch (ResourceNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 3:
				System.out.println("View Project :");
				try {
					guestDao.getProjectById();
				} catch (ResourceNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 4:
				System.out.println("Exit");
				System.out.println("Exit");
				System.exit(0);
				break;

			default:
				System.out.println("Ohhhooo!!! Please enter correct input.");
				break;
			}
		} while (choice != 4);
	}

}
