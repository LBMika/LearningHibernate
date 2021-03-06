package app;

import java.util.List;

import entity.Employee;
import manager.EmployeeManager;

public class App {

	/**
	 * App entry point
	 * @param args Args
	 */
	public static void main(String[] args) {
		EmployeeManager manager = new EmployeeManager();
		
		manager.setup();
		
		// First employee
		Employee e1 = new Employee("Toto", "Titi", "toto@titi.com", 10, "Manager", "0712695945", "56923 Toto City");
		try {
			if (!manager.create(e1)) {
				System.err.println("Can't create employee : "+e1);
			}
		} catch (Exception e) {
			System.err.println("Can't create employee : "+e1);
			// e.printStackTrace();
		}
		
		
		// Second employee
		Employee e2 = new Employee("Jean", "Jean", "jeanjean@jean.com", 10, "Jean", "0714545465", "11111 Jean");
		try {
			if (!manager.create(e2)) {
				System.err.println("Can't create employee : "+e2);
			}
		} catch (Exception e) {
			System.err.println("Can't create employee : "+e2);
			//e.printStackTrace();
		}
		
		// Third employee
		Employee e3 = new Employee("fsdggsd", "sdgsgsd", "sgdsg@gg.com", 52, "dddddd", "1111111111", "00000 Nulpart");
		try {
			if (!manager.create(e3)) {
				System.err.println("Can't create employee : "+e3);
			}
		} catch (Exception e) {
			System.err.println("Can't create employee : "+e3);
			//e.printStackTrace();
		}

		// Reading entry id 11
		System.out.println(manager.read(11L));

		// Deleting entry id 12 if present
		Employee toDelete = manager.read(12L);
		if (toDelete!=null) {
			try {
				manager.delete(toDelete);
			} catch (Exception e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
		}
		
		// Display all entries from employee table
		List<Employee> employees = manager.readAll();
		if (employees!=null) {
			for (Employee e : employees)
				System.out.println(e);
		}
		
		// Get employee by email
		System.out.println("*****************");
		Employee test = new Employee();
		test.setEmail("jeanjean@jean.com");
		try {
			System.out.println(manager.read(test));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Delete employee by email
		System.out.println("*****************");
		test = new Employee();
		test.setEmail("sgdsg@gg.com");
		try {
			manager.delete(test);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		manager.exit();
	}
}
