package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Programm {

	public static void main(String[] args) {
		//programinha para atualizar o salario de um vendedor
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE seller "
					+"SET BaseSalary = BaseSalary + ? "
						//NUNCA fazer uma atualização sem fazer uma restrição com o where
					+"WHERE"
					+"(DepartmentId = ?)");
			
			st.setDouble(1, 200);
			st.setInt(2, 2);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows affected: " + rowsAffected);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
