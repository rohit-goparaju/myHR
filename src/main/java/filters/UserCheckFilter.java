package filters;

import java.io.IOException;

import org.hibernate.SessionFactory;

import DAOS.EmployeeDAO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import myHR.Employee;

@WebFilter("/secure/*")
public class UserCheckFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		
		if(session.getAttribute("user") != null) {
			Employee user = (Employee) session.getAttribute("user");
			SessionFactory factory = (SessionFactory) getServletContext().getAttribute("SessionFactory");
			EmployeeDAO empDao = new EmployeeDAO(factory);
			Employee emp = empDao.getEmployee(user.getEmpEmail());
			if(emp != null) {
				chain.doFilter(request, response);
			}else {
				resp.sendRedirect(req.getContextPath() + "/index.jsp");
			}
		}else {
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		}
		
	}
}
