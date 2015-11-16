package am.gitc.jpa.service;

import am.gitc.jpa.data.*;
import am.gitc.jpa.manager.*;
import am.gitc.jpa.manager.impl.*;
import am.gitc.jpa.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * Created by Garik on 12/6/14.
 */
public class SendPasswordResetRequestService extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute(GlobalKeys.PERSON);

		IPersonManager manager = null;
		try {
			PersonToken token = new PersonToken();
			token.setPerson(person);
			token.setType(PersonToken.TokenType.PASSWORD_RESET);
			token.setValue(Generator.doToken());

			manager = new PersonManagerImpl();
			manager.addToken(token, true);

			session.setAttribute(GlobalKeys.MESSAGE, "The request to reset password was sent");
			response.sendRedirect("view-login");
		} catch (Exception e) {
			request.setAttribute(GlobalKeys.MESSAGE, "Internal Server Error : Failed to send password reset request");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			if (manager != null) {
				manager.close();
			}
		}
	}
}
