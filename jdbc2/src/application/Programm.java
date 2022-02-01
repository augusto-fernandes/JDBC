package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Programm {

	public static void main(String[] args) {
			
		Connection conn = null;
		//prepara uma consulta sql 
		Statement st = null;
		
		//guarda o resultado da consulta
		ResultSet rs = null;
		try {		
					//conecta no banco de dados
				conn = DB.getConnection();
				
				st = conn.createStatement();
					
					//executa um comando um sql
				rs = st.executeQuery("select * from department");
				
				while(rs.next()) {
						
					System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
				}
		}
		catch(SQLException e ) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}

	}

}
