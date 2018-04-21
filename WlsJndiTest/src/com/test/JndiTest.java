package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JndiTest {

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	public static void main(String args[]) {
		try {
			getDataSourceConn("t3://localhost:7001", "weblogic", "weblogic1", "wlsbjmsrpDataSource");
			String sql = "select * from wli_qs_report_attribute where msg_guid = 'uuid:e826c193e23a2372:6ee3b1da:14f5db1b622:665c'";
			boolean exist = userExist(sql);
			System.out.println(exist);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void getDataSourceConn(String url, String username,
			String password, String jndi) throws SQLException, NamingException {
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		ht.put(Context.PROVIDER_URL, url);
		ht.put(Context.SECURITY_PRINCIPAL, username);
		ht.put(Context.SECURITY_CREDENTIALS, password);

		Context context = new InitialContext(ht);

		// 注意：lookup 中的参数 是你在weblogic中配置的JNDI名称

		DataSource ds = (DataSource) context.lookup(jndi); // 配置的JNID名
		conn = ds.getConnection(); // 登陆weblogic的用户名、密码
	}

	public static boolean userExist(String sql)
			throws SQLException {
		pstmt = conn.prepareStatement(sql);
		// pstmt.setString(1, username);
		// pstmt.setString(2, password);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
