package main;

import java.sql.*;
import util.DatabaseUtil;

public class main {
	public static void main(String args[]){		
		//�ڹٺ� ����Ͽ� �����͵� �ѹ��� ����
		try {
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽��� ������ �� �ֵ��� ����
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
