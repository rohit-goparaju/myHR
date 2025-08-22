package DAOS;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import myHR.App;
import myHR.Employee;

public class EmployeeDAO {
	
	private SessionFactory factory;

	public EmployeeDAO(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	
	public void addEmployee(Employee emp) {
		try(Session session = factory.openSession()){
			Transaction tx = session.beginTransaction();
			session.persist(emp);
			tx.commit();
			App.log.info("employee added: " + emp.getEmpEmail());
		}catch(Exception e) {
			App.log.error(e.getClass()+" "+e.getMessage());
			for(StackTraceElement el : e.getStackTrace()) {
				App.log.error("ClassName: " + el.getClassName());
				App.log.error("MethodName: " + el.getMethodName());
				App.log.error("LineNumber: " + el.getLineNumber()+"");
			}
			e.printStackTrace();
		}
	}
	
	public void removeEmployee(Employee emp) {
		try(Session session = factory.openSession()){
			Transaction tx = session.beginTransaction();
			session.remove(emp);
			tx.commit();
			App.log.info("employee removed: " + emp.getEmpEmail());
		}catch(Exception e) {
			App.log.error(e.getClass()+" "+e.getMessage());
			for(StackTraceElement el : e.getStackTrace()) {
				App.log.error("ClassName: " + el.getClassName());
				App.log.error("MethodName: " + el.getMethodName());
				App.log.error("LineNumber: " + el.getLineNumber()+"");
			}
			e.printStackTrace();
		}
	}
	
	public Employee getEmployee(String empEmail) {
		Employee emp = null;
		String queryString = "from Employee where empEmail = :mail";
		try(Session session = factory.openSession()){
			Transaction tx = session.beginTransaction();
			Query<Employee> query = session.createQuery(queryString, Employee.class);
			query.setParameter("mail", empEmail);
			emp = query.getSingleResultOrNull();
			tx.commit();
		}catch(Exception e) {
			App.log.error(e.getClass()+" "+e.getMessage());
			for(StackTraceElement el : e.getStackTrace()) {
				App.log.error("ClassName: " + el.getClassName());
				App.log.error("MethodName: " + el.getMethodName());
				App.log.error("LineNumber: " + el.getLineNumber()+"");
			}
			e.printStackTrace();
		}
		if(emp == null) {
			App.log.warn("Employee not found: " + empEmail);
		}else {
			App.log.info("Employee found: " + empEmail);
		}
		return emp;
	}
	
}
