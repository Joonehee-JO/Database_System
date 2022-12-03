package student;

import java.sql.*;
import java.util.Vector;

import util.DatabaseUtil;

public class StudentDAO {
	ResultSet rs; 

	public void selectAllStudent() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select * from student";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("학번"+" / "+"이름"+" / "+"나이"+" / "+"학년" + " / " + "전화번호");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+
						"세 " + rs.getInt(4) + "학년 " + rs.getString(5));
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkDuplicateSid(String sid) {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select sid from student where sid = ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, sid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				conn.close();
				return true;
			}
			else {
				conn.close();
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void insertStudent(String sid, String sname, int sage, int sgrade, String stel) {
		Connection conn = DatabaseUtil.getConnection();	
		String SQL = "insert into student VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, sid);
			pstmt.setString(2, sname);
			pstmt.setInt(3, sage);
			pstmt.setInt(4, sgrade);
			pstmt.setString(5, stel);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchStudent(String sid) {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select * from student where sid LIKE ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + sid + "%");
			rs = pstmt.executeQuery();
			System.out.println("학번"+" / "+"이름"+" / "+"나이"+" / "+"학년" + " / " + "전화번호");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+
						"세 " + rs.getInt(4) + "학년 " + rs.getString(5));
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
