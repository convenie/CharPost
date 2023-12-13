package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AIDAO;
import model.AccountInfoBean;

@WebServlet("/CardSearch")
public class CardSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/cardsearch.jsp");
			dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String input_othername = request.getParameter("othername");

		try{
			AccountInfoBean input_user = new AccountInfoBean();
			input_user.setName(input_othername);
			input_user.setComment(input_othername);
			ArrayList<AccountInfoBean> user_list = AIDAO.getInstance().SelectUserData(input_user);
			request.setAttribute("user_list", user_list);
            request.getRequestDispatcher("/WEB-INF/jsp/cardsearch.jsp").forward(request, response);

		}catch(Exception e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

}
