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
import model.org.LoginModel;
import msg.Messages;
import session.id.SessionId;
import view.ftl.TemplateLocal;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("You are inside the service method : FreeMarkerServlet");
		User user ;
		LoginModel lm ;
		Messages msg;
		Template template;
		HttpSession session;
		Map<String, Object> map ;
		Writer out;
		FormAction formAction;

		try {
			session = request.getSession(false);
			if(session != null){
				user  = (User) session.getAttribute("user");
				if(user == null){
					session.invalidate();
					response.sendRedirect("index.html");
				}
				for (FormAction ac :FormAction.getListOfHomeFormActions())
				{
					System.out.println(ac);
				}
				map = new HashMap<>();
				map.put("user", "Hello "+user.getUsername().toUpperCase()+"...!");
				map.put("msg", "Try again");
				map.put("style", "homestyle");
				map.put("forms", FormAction.getListOfHomeFormActions());
				System.out.println(map);
				out = response.getWriter();
				template = TemplateLocal.getTemplate("home.ftl");
				template.process(map, out);

			}
			else{
				user = new User();

				user.setUsername(request.getParameter("username").trim().toLowerCase());
				user.setPassword(request.getParameter("password").trim());
				//----------------------------------------------------
				//	user.setUsername("postgres");			Testing
				//user.setPassword("enh@123");

				//----------------------------------------------------


				lm  = new LoginModel();
				msg = lm.validate(user);
				if (msg.getSuccessmsg() != null){

					session = request.getSession();

					SessionId.setSessionId(session.getId().toString());
					System.out.println(session.getId());

					session.setAttribute("user", user);
					for (FormAction ac :FormAction.getListOfHomeFormActions())
					{
						System.out.println(ac);
					}
					map = new HashMap<>();
					map.put("user", "Hello "+user.getUsername().toUpperCase()+"...!");
					map.put("msg", msg.getReson()+" is "+msg.getSuccessmsg());
					map.put("style", "homestyle");
					map.put("forms", FormAction.getListOfHomeFormActions());
					System.out.println(map);



					out = response.getWriter();
					template = TemplateLocal.getTemplate("home.ftl");
					template.process(map, out);

				}else {
					map = new HashMap<>();
					map.put("msg", msg.getReson()+" is "+msg.getFailmsg());
					map.put("user", "Hello "+user.getUsername().toUpperCase()+"...!");
					map.put("style", "indexstyle");
					formAction = new FormAction();
					formAction.setName("LoginServlet");

					map.put("formAction", formAction);

					out = response.getWriter();
					template = TemplateLocal.getTemplate("loginOut.ftl");
					template.process(map, out);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			user = null;
		}

	}

}

