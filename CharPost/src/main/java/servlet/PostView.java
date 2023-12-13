package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import model.PostBean;
import model.UserBean;

@WebServlet("/PostView")
public class PostView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		UserBean loginUser=(UserBean) session.getAttribute("loginUser");
		PostBean postBean =  (PostBean)session.getAttribute("postBean");
		PostDAO postDao=new PostDAO();
		List<PostBean> postList=postDao.findAll();
		request.setAttribute("postList",postList);
		session=request.getSession();
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/postview.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

	}

}
