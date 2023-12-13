package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CustomerBean;
import model.CustomerInsertBean;
public class BCDAO {
	private final String JDBC_URL="jdbc:mysql://localhost/charpostdb";
	private final String DB_USER="****";
	private final String DB_PASS="****";


	public List<CustomerBean> findAll(){
		List<CustomerBean> customerList=new ArrayList<CustomerBean>();

		try(Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="SELECT * FROM ACCOUNTLIST";
			PreparedStatement pStmt=conn.prepareStatement(sql);

			ResultSet rs=pStmt.executeQuery();

			while(rs.next()) {
				int ID=rs.getInt("ID");
				String name=rs.getString("name");
				String password=rs.getString("password");


				CustomerBean customerBean=new CustomerBean(ID,name,password);
				customerList.add(customerBean);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return customerList;
	}
	public boolean insert(CustomerInsertBean presence2) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="INSERT INTO ACCOUNTLIST(ID,name,password) VALUES (?,?,?)";
			PreparedStatement pStmt=conn.prepareStatement(sql);

			pStmt.setLong(1,presence2.getCount());
			pStmt.setString(2,presence2.getName());
			pStmt.setString(3,presence2.getPassword());

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
}

