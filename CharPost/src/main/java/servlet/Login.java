package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import model.AccountInfoBean;
import model.PostBean;
import model.UserBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String JDBC_URL="jdbc:mysql://localhost/charpostdb";
	private final String DB_USER="****";
	private final String DB_PASS="****";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    String userName = request.getParameter("user_name");
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
                System.out.println( sb );
        } catch (Exception e) {
                e.printStackTrace();
        }
	    String id=null;
	    String path = "";
	    AccountInfoBean accountinfo = new AccountInfoBean();
	    PostBean postBean =new PostBean();
	    try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
	    	String sql = "SELECT id,name FROM accountlist WHERE name=? AND password=?";
	    	PreparedStatement pstmt=conn.prepareStatement(sql);{
	            pstmt.setString(1, userName);
	            pstmt.setString(2, hash);
	            ResultSet res = pstmt.executeQuery();
	            if (res.next()) {
	            	id=res.getString("id");
	                request.setAttribute("user_name", res.getString("name"));
	                request.setAttribute("id", res.getString("id"));
	                path = "/WEB-INF/jsp/main.jsp";
	            } else {
	                request.setAttribute("loginFailure", "ログインに失敗しました");
	                System.out.println("ログイン失敗");
	                path = "/WEB-INF/jsp/index.jsp";
	            }
	    	}


	    	UserBean userbean=new UserBean(id,userName,password);
	    	accountinfo.setName(userName);
	    	postBean.setName(userName);
			HttpSession session=request.getSession();
			//↓投稿閲覧のためのもの
			postBean =  (PostBean)session.getAttribute("postBean");
			PostDAO postDao=new PostDAO();
			List<PostBean> postList=postDao.findAll();
			request.setAttribute("postList",postList);
			//↑投稿閲覧のためのもの
			session.setAttribute("loginUser", userbean);
			session.setAttribute("accountinfo", accountinfo);
			session.setAttribute("postBean", postBean);


	    }catch (SQLException e) {
	        e.printStackTrace();
	    }
	    RequestDispatcher rd = request.getRequestDispatcher(path);
	    rd.forward(request, response);
	}
}
