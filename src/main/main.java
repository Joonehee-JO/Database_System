package main;

import java.sql.*;
import util.DatabaseUtil;

public class main {
	public static void main(String args[]){		
		//자바빈 사용하여 데이터들 한번에 저장
		try {
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스에 접근할 수 있도록 설정
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM Book");
			while(rs.next())
				System.out.println(rs.getString(1)+" "+rs.getString(2)+
						" "+rs.getString(3));
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
