package listeners;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import myHR.App;

/**
 * Application Lifecycle Listener implementation class HibernateListener
 *
 */
@WebListener
public class HibernateListener implements ServletContextListener {

	private SessionFactory factory;
	
    public void contextInitialized(ServletContextEvent sce)  { 
    	
    	factory = new Configuration().configure().buildSessionFactory();
    	
    	sce.getServletContext().setAttribute("SessionFactory", factory);

    	App.log.info("Hibernate session factory created");
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	factory.close();
    	App.log.info("Hibernate session factory closed");
    }
	
}
