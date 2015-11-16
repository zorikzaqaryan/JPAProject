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
public class EmailConfirmationService extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String email = request.getParameter("email");
		String tokenValue = request.getParameter("token");

		if (!Validator.isValidEmail(email) || Validator.isEmpty(tokenValue)) {

			request.setAttribute(GlobalKeys.MESSAGE, "Invalid parameters");
			request.getRequestDispatcher("/invalid-email-confirm.jsp").forward(request, response);

		} else {

			IPersonManager manager = null;
			try {
				manager = new PersonManagerImpl();
				PersonToken token = manager.getTokenByValue(tokenValue);
				if (token != null &&
					token.getType() == PersonToken.TokenType.EMAIL_CONFIRMATION &&
					email.equals(token.getPerson().getEmail())) {

					Person person = token.getPerson();
					person.setStatus(Person.PersonStatus.ACTIVE);
					manager.editPerson(person);

					HttpSession session = request.getSession();
					session.setAttribute(GlobalKeys.MESSAGE, "Your account is activated");

					response.sendRedirect("view-login");
				} else {
					request.setAttribute(GlobalKeys.MESSAGE, "Invalid parameters");
					request.getRequestDispatcher("/invalid-email-confirm.jsp").forward(request, response);
				}
			} catch (Exception e) {
				request.setAttribute(GlobalKeys.MESSAGE, "Internal Server Error : Failed to confirm person's email");
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} finally {
				if (manager != null) {
					manager.close();
				}
			}
		}
	}
}
