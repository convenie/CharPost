package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.PostBean;
import model.UserBean;
public class PostDAO {
	private final String JDBC_URL="jdbc:mysql://localhost/charpostdb";
	private final String DB_USER="****";
	private final String DB_PASS="****";


	UserBean userBean=new UserBean();
	public List<PostBean> findAll(){
		List<PostBean> postList=new ArrayList<PostBean>();

		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="SELECT * FROM postlist";
			PreparedStatement pStmt=conn.prepareStatement(sql);

			ResultSet rs=pStmt.executeQuery();

			while(rs.next()) {
				String name=rs.getString("name");
				String chara=rs.getString("chara");
				String charcolor=rs.getString("charcolor");
				String charfont=rs.getString("charfont");
				String time=rs.getString("posttime");
				int like=rs.getInt("greatlike");
				int dislike=rs.getInt("dislike");
				PostBean postBean=new PostBean(name,chara,charcolor,charfont,time,like,dislike);
				postList.add(postBean);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return postList;
	}
	public static PostDAO getInstance() {
		return new PostDAO();
	}
	public boolean insert(PostBean postBean) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        
	        // String型に変換
	        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        String posttime = simpleDateFormat1.format(timestamp);
			String sql="insert into postlist(name,chara,charcolor,charfont,posttime) VALUES (?,?,?,?,?)";
			PreparedStatement pStmt=conn.prepareStatement(sql);

			System.out.print(postBean.getName());
			pStmt.setString(1,postBean.getName());
			pStmt.setString(2,postBean.getChara());
			pStmt.setString(3,postBean.getCharcolor());
			pStmt.setString(4,postBean.getCharfont());
			pStmt.setString(5,posttime);
			int result=pStmt.executeUpdate();

			if(result!=1) {
				return false;
			}
		}catch(SQLException e) {
			System.out.println("追加時に問題が発生しました。");
			e.printStackTrace();
			return false;
		}
		System.out.println("正常追加完了");
		return true;
	}
	public boolean updatelikedislike(PostBean postBean) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

			String sql="update postlist set greatlike=?,dislike=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);

			pStmt.setInt(1,postBean.getLike());
			pStmt.setInt(2,postBean.getDislike());
			int result=pStmt.executeUpdate();

			if(result!=1) {
				return false;
			}
		}catch(SQLException e) {
			System.out.println("いいね更新時に問題が発生しました。");
			e.printStackTrace();
			return false;
		}
		System.out.println("いいね更新完了");
		return true;
	}
	public ArrayList<PostBean> SelectUserData() throws SQLException {
		// 初期値をセット
		Connection db_con = null;

		// 複数のユーザ情報を格納するため、Beanを格納する配列を作成
		ArrayList<PostBean> array_userinfo = new ArrayList<PostBean>();

		try (Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

			// 実行するSQL文をセット（空文字）
			String sql = "SELECT * FROM postlist ORDER BY RAND() LIMIT 3";

			PreparedStatement pStmt=conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			// 検索結果格納のため、Beanクラスをインスタンス
			PostBean resultUserInfo = new PostBean();

			// SQL文の実行結果件数分ループ処理
			while(rs.next()) {
				// ユーザIDと名前をBeanクラスへセット
				resultUserInfo.setName(rs.getString("NAME"));
				resultUserInfo.setChara(rs.getString("Chara"));
				resultUserInfo.setCharcolor(rs.getString("Charcolor"));
				resultUserInfo.setCharfont(rs.getString("Charfont"));

				// リストにBeanクラスごと格納
				array_userinfo.add(resultUserInfo);

				//Beanクラスを初期化
				resultUserInfo = new PostBean();
				}

		} catch(SQLException sql_e) {
			// エラーハンドリング
			System.out.println("sql実行失敗");
			sql_e.printStackTrace();

		} finally {
			// DB接続を解除
			if (db_con != null) {
					db_con.close();
			}
		}
		// リストを返す
		return array_userinfo;
	}
}

