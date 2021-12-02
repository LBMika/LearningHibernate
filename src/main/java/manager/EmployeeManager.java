package manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entity.Employee;

public class EmployeeManager {
	protected SessionFactory sessionFactory;
	
	/**
	 * Loading an hibernate session
	 */
	protected void setup() {
		// Get hibernate configuration from cfg file to create a registry
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			// Try to connect to DB
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}
	}
	
	/**
	 * Deleting an hibernate session
	 */
	protected void exit() {
		if (sessionFactory!=null) sessionFactory.close();
	}
	
	/**
	 * Creating an entry
	 */
	protected boolean create(Employee e) {
		boolean result;
		if (e!=null && e.isValidForCreation()) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(e);
			session.getTransaction().commit();
			session.close();
			result = true;
		}
		else
			result = false;
		return result;
	}
	
	/**
	 * Reading an entry
	 */
	protected Employee read(long id) {
		Session session = sessionFactory.openSession();
		Employee e = session.get(Employee.class, id);
		session.close();
		return e;
	}
	
	/**
	 * Reading all entry in table employee
	 * @return All the employee of Touloulou
	 */
	protected List<Employee> getAll() {
		List<Employee> result;
		Session session = sessionFactory.openSession();
		result = session.createQuery("SELECT * FROM touloulou.employee", Employee.class).getResultList();
		session.close();
		return result;
	}
	
	/**
	 * Updating an entry
	 */
	protected void update(long id, Employee employee) {
		Employee old = this.read(id);
		old.init(employee);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(old);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Deleting an entry
	 */
	protected void delete(Employee e) {
		if (e.getId()!=-1) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(e);
			session.getTransaction().commit();
			session.close();
		}
	}
	
	/**
	 * App entry point
	 * @param args Args
	 */
	public static void main(String[] args) {
		EmployeeManager manager = new EmployeeManager();
		
		manager.setup();
		
		
		
		manager.exit();
	}
}
