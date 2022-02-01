package application;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Programm {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			//nao é pra confirmar as operaçoes automaticamente, todas as transaçoes vao ficar pedentes
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE departmentId = 1");
			
			//int x = 1;
			//if(x<2) {
			//	throw new SQLException("Fake error");
			//}
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE departmentId = 2");
			// só agora as mudanças serao feitas
			conn.commit();
			
			System.out.println("rows 1: "+ rows1);
			System.out.println("rows 2: "+ rows2);
		}
		catch(SQLException e) {
			try {
				// volta as mudanças feitas
				conn.rollback();
				throw new DbException("Transaction rolled back! caused by: " + e.getMessage());
			} catch (SQLException e1) {
				
				throw new DbException("Error trying to rollback cause by:" + e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
