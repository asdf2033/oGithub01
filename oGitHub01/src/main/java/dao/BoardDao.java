package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDao {
	private static BoardDao instance;
	private BoardDao() {
		// 외부에서 생성자로 접근할 수 없도록 제어
	}
	public static BoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	public Connection getConnect() {
		
		Connection conn = null;		
		Context ctx = null;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public int getTotCnt() throws SQLException {
		BoardDao bd = BoardDao.getInstance();
		Connection conn = null;
		Statement stmt = null;
		String sql = "Select count(*) From board";
		int result = 0;
		ResultSet rs = null;
		try {
			conn = bd.getConnect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		 
		
		return result;
	}
	}
