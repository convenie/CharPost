package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BCDAO;
import dao.PostDAO;
import model.CustomerBean;
import model.CustomerInsertBean;
import model.PostBean;
import model.UserBean;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		UserBean loginUser=(UserBean) session.getAttribute("loginUser");
		BCDAO bcDAO=new BCDAO();
		List<CustomerBean> bcList=bcDAO.findAll();
		request.setAttribute("bcList",bcList);
		
		String forwardPath=null;
		String action=request.getParameter("action");
		if(action!=null&&action.equals("accR")) {
			forwardPath="/WEB-INF/jsp/accountRegister.jsp";
		}else if(action!=null&&action.equals("logR")){
			forwardPath="/WEB-INF/jsp/loginResult.jsp";
		}else if(action!=null&&action.equals("logout")){
			forwardPath="/WEB-INF/jsp/logout.jsp";
		}else if(action!=null&&action.equals("main")){
			PostBean postBean =  (PostBean)session.getAttribute("postBean");
			PostDAO postDao=new PostDAO();
			List<PostBean> postList=postDao.findAll();
			request.setAttribute("postList",postList);
			session=request.getSession();
			forwardPath="/WEB-INF/jsp/main.jsp";
		}

		RequestDispatcher dispatcher=request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		CustomerInsertBean presence = new CustomerInsertBean();
		String password=request.getParameter("password");
		String hash=null;
        byte[] cipher_byte;
        try{
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(password.getBytes());
                cipher_byte = md.digest();
                StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
                for(byte b: cipher_byte) {
                        sb.append(String.format("%02x", b&0xff) );
                }
                hash=sb.toString();

        } catch (Exception e) {
                e.printStackTrace();
        }
		String id=request.getParameter("count");
		String name=request.getParameter("name");

		
		presence.setID(id+1);
		presence.setName(name);
		presence.setPassword(hash);
		

		
		BCDAO bcDao=new BCDAO();
		bcDao.insert(presence);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("topPage.jsp");
		dispatcher.forward(request, response);
	}

}
