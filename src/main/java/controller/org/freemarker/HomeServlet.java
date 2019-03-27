package controller.org.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dom.pojo.org.User;
import formtags.FormAction;
import freemarker.template.Template;
import session.id.SessionId;
import view.ftl.TemplateLocal;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("You are in  HomeServlet");
		User user = null;
		Template template;
		HttpSession session;
		Map<String, Object> map ;
		Writer out;

		try {
			session = request.getSession(false);
			session  = request.getSession(false);
			if (SessionId.getSessionId().equals(session.getId())) 
				user  = (User) session.getAttribute("user");
			System.out.println(session.getId());
			System.out.println(user);
			if(user != null){

				SessionId.setSessionId(session.getId().toString());
				System.out.println(session.getId());

				session.setAttribute("user", user);
				System.out.println(FormAction.getListOfHomeFormActions());
				map = new HashMap<>();
				map.put("user", "Hello "+user.getUsername().toUpperCase()+"...!");
				map.put("msg", "Homepage");
				map.put("style", "homestyle");
				map.put("forms", FormAction.getListOfHomeFormActions());
				
				System.out.println(map);
				out = response.getWriter();
				template = TemplateLocal.getTemplate("home.ftl");
				template.process(map, out);

			}else {
				map = new HashMap<>();
				map.put("msg", "Please try again later..");
				map.put("user", "Sorry");
				map.put("style", "indexstyle");
				out = response.getWriter();
				template = TemplateLocal.getTemplate("loginOut.ftl");
				template.process(map, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("index.html");
		}
		finally {
			user = null;
		}


	}

}
