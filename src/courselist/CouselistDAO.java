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
			System.out.println("과목코드"+" / "+"과목명"+" / "+"학점 수"+" / "+"전공구분");
			while(rs.next()) {
				System.out.print(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+
						"학점 ");
				if(rs.getString(4).equals("10")) {
					System.out.println("전공필수");
				}
				else {
					System.out.println("전공선택");
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
						"학점 ");
				if(rs.getString(3).equals("10")) {
					System.out.println("전공필수");
				}
				else {
					System.out.println("전공선택");
				}
			}

			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
