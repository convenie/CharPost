package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AccountInfoBean;
import model.UserBean;
public class AIDAO {

	private final String JDBC_URL="jdbc:mysql://localhost/charpostdb";
	private final String DB_USER="****";
	private final String DB_PASS="****";

	UserBean userBean=new UserBean();

	public List<AccountInfoBean> findAll(){
		List<AccountInfoBean> accountList=new ArrayList<AccountInfoBean>();

		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="SELECT * FROM ACCOUNTINFO";
			PreparedStatement pStmt=conn.prepareStatement(sql);

			ResultSet rs=pStmt.executeQuery();

			while(rs.next()) {
				String name=rs.getString("name");
				String comment=rs.getString("comment");
				String birthmonth=rs.getString("birthmonth");
				String birthday=rs.getString("birthday");
				AccountInfoBean accountInfoBean=new AccountInfoBean(name,comment,birthmonth,birthday);
				accountList.add(accountInfoBean);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return accountList;
	}
	public static AIDAO getInstance() {
		return new AIDAO();
	}
	public String select(AccountInfoBean accountInfoBean) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="SELECT * FROM ACCOUNTINFO WHERE name=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);


			pStmt.setString(1,accountInfoBean.getName());
			ResultSet rs=pStmt.executeQuery();
            if (rs.next()) {
            	rs.getString("name");
            }
            System.out.println("!!レコードは正常に検索(UPDATE)されました");
            return rs.getString("name");

		}catch(SQLException e) {
			System.out.println("!!レコードは検索(UPDATE)されませんでした");
			e.printStackTrace();
			return "";
		}

	}
	public boolean update(AccountInfoBean accountInfoBean) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="UPDATE ACCOUNTINFO SET NAME=?,COMMENT=?,BIRTHMONTH=?,BIRTHDAY=? WHERE NAME=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);


			pStmt.setString(1,accountInfoBean.getName());
			pStmt.setString(2,accountInfoBean.getComment());
			pStmt.setString(3,accountInfoBean.getBirthmonth());
			pStmt.setString(4,accountInfoBean.getBirthday());
			pStmt.setString(5,accountInfoBean.getName());
			int result=pStmt.executeUpdate();

			if(result!=1) {
				return false;
			}
		}catch(SQLException e) {
			System.out.println("!!レコードは更新(UPDATE)されませんでした");
			e.printStackTrace();
			return false;
		}
		System.out.println("!!レコードは正常に更新(UPDATE)されました");
		return true;
	}
	public boolean insert(AccountInfoBean accountInfoBean) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="INSERT INTO ACCOUNTINFO(name,comment,birthmonth,birthday) VALUES (?,?,?,?)";
			PreparedStatement pStmt=conn.prepareStatement(sql);

			System.out.println(accountInfoBean.getName()+":AIDAO");
			pStmt.setString(1,accountInfoBean.getName());
			pStmt.setString(2,accountInfoBean.getComment());
			pStmt.setString(3,accountInfoBean.getBirthmonth());
			pStmt.setString(4,accountInfoBean.getBirthday());
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
	public ArrayList<AccountInfoBean> SelectUserData(AccountInfoBean input_user) throws SQLException {
		// 初期値をセット
		Connection db_con = null;
		String name = input_user.getName();
		String comment = input_user.getComment();

		// 複数のユーザ情報を格納するため、Beanを格納する配列を作成
		ArrayList<AccountInfoBean> array_userinfo = new ArrayList<AccountInfoBean>();

		try (Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

			// 実行するSQL文をセット（空文字）
			String sql = "";

			// 実行するSQL文をセット
			// JSPでIDを指定しない場合は全件検索
			if(name == "" && comment=="") {
				//sql = "select * from friendlist";
			}else {
				sql = "select * from accountinfo where name  LIKE ? or comment LIKE ? ";
			}

			// DBで実行するSQL文を「prepareStatement」インスタンスに格納する
			PreparedStatement pStmt=conn.prepareStatement(sql);
			// ユーザIDを指定している場合は、条件指定する
			if(name!="" || comment!="") {
				pStmt.setString(1, "%"+name+"%");
				pStmt.setString(2, "%"+comment+"%");
			}

			// SQL文の実行結果を格納
			ResultSet rs = pStmt.executeQuery();

			// 検索結果格納のため、Beanクラスをインスタンス
			AccountInfoBean resultUserInfo = new AccountInfoBean();

			// SQL文の実行結果件数分ループ処理
			while(rs.next()) {
				// ユーザIDと名前をBeanクラスへセット
				resultUserInfo.setName(rs.getString("NAME"));
				resultUserInfo.setComment(rs.getString("Comment"));
				resultUserInfo.setBirthmonth(rs.getString("Birthmonth"));
				resultUserInfo.setBirthday(rs.getString("Birthday"));

				// リストにBeanクラスごと格納
				array_userinfo.add(resultUserInfo);

				//Beanクラスを初期化
				resultUserInfo = new AccountInfoBean();
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

