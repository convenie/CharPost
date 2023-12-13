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

@WebServlet("/Posting")
public class Posting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostDAO postDao=new PostDAO();
		List<PostBean> postList=postDao.findAll();
		request.setAttribute("postlist", postList);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/charpost.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		UserBean loginUser=(UserBean) session.getAttribute("loginUser");
		//PostBean postBean =  (PostBean)session.getAttribute("postBean");
		PostBean postBean=new PostBean();
		String name=loginUser.getName();
		postBean.setName(name);
		String chara=request.getParameter("chara");
		String charcolor=request.getParameter("charcolor");
		String charfont=request.getParameter("charfont");
		postBean.setChara(chara);
		postBean.setCharcolor(charcolor);
		postBean.setCharfont(charfont);

		PostDAO postDao=new PostDAO();
		postDao.insert(postBean);
		
		session=request.getSession();
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/charpost.jsp");
		dispatcher.forward(request, response);
	}

}
