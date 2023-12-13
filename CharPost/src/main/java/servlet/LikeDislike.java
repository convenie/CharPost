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
import model.LikeDislikeLogic;
import model.PostBean;

/**
 * Servlet implementation class LikeDislike
 */
@WebServlet("/LikeDislike")
public class LikeDislike extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PostBean postBean=(PostBean) session.getAttribute("postBean");
		PostDAO postDAO=new PostDAO();
		
		if(postBean == null) {
			postBean=new PostBean();
		}
		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		
		LikeDislikeLogic likeDislikeLogic=new LikeDislikeLogic();
		if(action!=null&&action.equals("like")) {
			likeDislikeLogic.like(postBean);
		}else if(action!=null&&action.equals("dislike")) {
			likeDislikeLogic.dislike(postBean);
		}
		postDAO.updatelikedislike(postBean);
		//↓投稿閲覧
		List<PostBean> postList=postDAO.findAll();
		request.setAttribute("postList",postList);
		//↑投稿閲覧
		session.setAttribute("postBean", postBean);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
