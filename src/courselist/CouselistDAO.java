package courselist;

import java.sql.*;
import java.util.Vector;

import util.DatabaseUtil;

public class CouselistDAO {
	ResultSet rs; 
	public void selectAllcourselist() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select * from courselist";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("�����ڵ�"+" / "+"�����"+" / "+"���� ��"+" / "+"��������");
			while(rs.next()) {
				System.out.print(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+
						"���� ");
				if(rs.getString(4).equals("10")) {
					System.out.println("�����ʼ�");
				}
				else {
					System.out.println("��������");
				}
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchCourselist(String cname) {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select cname,cgrade,cdiv from courselist where cname LIKE ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + cname + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.print(rs.getString(1)+" "+rs.getInt(2)+
						"���� ");
				if(rs.getString(3).equals("10")) {
					System.out.println("�����ʼ�");
				}
				else {
					System.out.println("��������");
				}
			}

			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
