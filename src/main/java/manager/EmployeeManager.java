package manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	public void setup() {
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
	public void exit() {
		if (sessionFactory!=null) sessionFactory.close();
	}
	
	/**
	 * Creating an entry of Employee
	 */
	public boolean create(Employee employee) throws Exception{
		boolean result;
		if (employee!=null && employee.isValidForCreation()) {
			Session session = null;
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(employee);
				session.getTransaction().commit();
			}
			catch(Exception e) {
				result = false;
				throw e;
			}
			finally {
				if (session!=null) session.close();
			}
			result = true;
		}
		else
			result = false;
		return result;
	}
	
	/**
	 * Reading an entry of Employee by its id
	 */
	public Employee read(long id) {
		Session session = sessionFactory.openSession();
		Employee e = session.get(Employee.class, id);
		session.close();
		return e;
	}
	
	/**
	 * Reading an entry of Employee by its email
	 */
	public Employee read(Employee employee) throws Exception {
		Session session = null;
		Employee result;
		if (employee.getId()==-1) {
			try {
				session = sessionFactory.openSession();
				CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
				CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
				Root<Employee> root = criteriaQuery.from(Employee.class);
				criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), employee.getEmail()));
				result = session.createQuery(criteriaQuery).getResultList().get(0);
			}
			catch (Exception e) {
				result = null;
				throw e;
			}
			finally {
				if (session!=null) session.close();
			}
		}
		else
			result = this.read(employee.getId());
		return result;
	}
	
	/**
	 * Reading all entry in table employee
	 * @return All the employee of Touloulou
	 */
	public List<Employee> readAll() {
		List<Employee> result;
		Session session = null;
	    try
	    {
	    	session = sessionFactory.openSession();
			CriteriaQuery<Employee> criteriaQuery = session.getCriteriaBuilder().createQuery(Employee.class);
	        result = session.createQuery(criteriaQuery).getResultList();
	    } catch (Exception e) {
	        result =  new ArrayList<Employee>();
	    }
	    finally {
	    	if (session!=null) session.close();
	    }
	    
		return result;
	}
	
	/**
	 * Updating an entry of Employee
	 */
	public void update(long id, Employee employee) {
		Employee old = this.read(id);
		old.init(employee);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(old);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Deleting an entry of Employee
	 * @throws Exception 
	 */
	public void delete(Employee employee) throws Exception {
		// Delete by id
		if (employee.getId()!=-1) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(employee);
			session.getTransaction().commit();
			session.close();
		}
		else
		// Delete by email
		if (employee.getEmail()!=null && !employee.getEmail().isBlank()) {
			Session session = null;
			try {
				session = sessionFactory.openSession();
				CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
				CriteriaDelete<Employee> criteriaDelete = criteriaBuilder.createCriteriaDelete(Employee.class);
				Root<Employee> root = criteriaDelete.from(Employee.class);
				criteriaDelete.where(criteriaBuilder.equal(root.get("email"), employee.getEmail()));
				session.beginTransaction();
				session.createQuery(criteriaDelete).executeUpdate();
				session.getTransaction().commit();
			}
			catch (Exception e) {
				throw e;
			}
			finally {
				if (session!=null) session.close();
			}
		}
	}
}
