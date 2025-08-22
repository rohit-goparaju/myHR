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

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginServlet")
public class LoginFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String username = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		SessionFactory factory = (SessionFactory) getServletContext().getAttribute("SessionFactory");
		
		EmployeeDAO empDao = new EmployeeDAO(factory);
		
		Employee emp = empDao.getEmployee(username);
		
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		if(emp != null) {
			if(emp.getEmpPassword().equals(pwd)) {
				session.setAttribute("user", emp);
				session.removeAttribute("errorMessage");
				chain.doFilter(request, response);
			}else {
				session.setAttribute("errorMessage", "invalid username or password");
				resp.sendRedirect("index.jsp");
			}
		}else {
			session.setAttribute("errorMessage", "invalid username or password");
			resp.sendRedirect("index.jsp");
		}
	}
}
