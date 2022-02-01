package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;

public class Programm {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			/*
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
						// o interrogaçao é um "place holder" um lugar que apenas depois voce vai colocar os valores
					+"(?,?, ?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
				
				//coloca o nome "Carl purple" no primeiro interrogaçao, que é o nome
			st.setString(1, "Carl Purple");
			
			st.setString(2, "carl@gmail.com");
			
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			
			st.setDouble(4, 3000);
			
			st.setInt(5, 4);
				*/
			
				//adicionando dois novos departamentos
			st= conn.prepareStatement("insert into department (Name) values ('D1'), ('D2')",
					Statement.RETURN_GENERATED_KEYS);
				//executeUpdate para alterar os dados
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected >0) {
					
					//codigo pra mostar o id do novo elemento
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " +id);
				}
			}
			else {
				System.out.println("No Rown affected!");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		finally {
			DB.closeStatement(st);

				//sempre fechar a conexao depois
			DB.closeConnection();
		}

	}

}
