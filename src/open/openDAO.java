package open;

import java.sql.*;

import util.DatabaseUtil;

public class openDAO {
	ResultSet rs; 
	
	//빈강의실 확인 메소드
	public void checkEmptyLectureroom() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select lid,did from lectureroom,datecode where (lid,did) NOT IN (SELECT lid,did FROM usedlectureroom)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("강의실"+" / "+"사용시간");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" / "+rs.getString(2));
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//개설교과목에 삽입 메소드
	public void insertOpensubject(String oyear, String cid, String pid) {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "insert into opensubject values (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, oyear);
			pstmt.setString(2, cid);
			pstmt.setString(3, pid);
			pstmt.executeUpdate();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//사용강의실 삽입 메소드
	public void insertUsedlectureroom(String oyear, String cid, String lid, String did) {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "insert into opensubject values (?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getFinalUid());
			pstmt.setString(2, oyear);
			pstmt.setString(3, cid);
			pstmt.setString(4, lid);
			pstmt.setString(5, did);
			pstmt.executeUpdate();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkProfessorTimetable() {
		
	}
	
	//마지막 uid번호 넘겨주기
	public int getFinalUid() {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "select uid from usedlectureroom order by cast(uid as unsigned) desc";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
