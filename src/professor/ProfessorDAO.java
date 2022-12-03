package professor;

import java.sql.*;

import util.DatabaseUtil;

public class ProfessorDAO {
	ResultSet rs; 
	
	//교수번호의 마지막 번호 가져오기
	public int getNextPid() {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "select pid from professor order by cast(pid as unsigned) desc";
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
	
	//모든 교수님들 정보 출력
	public void selectAllProfessor() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select * from professor order by cast(pid as unsigned)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("교수번호"+" / "+"이름"+" / "+"나이"+" / "+"전화번호" + " / " + "연구실");
			while(rs.next()) {
				System.out.println(rs.getString(1)+"    "+rs.getString(2)+" "+rs.getInt(3)+
						"  세 " + rs.getString(4)  +"   "+ rs.getString(5) + "호");
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//교수님 신규등록
	public void insertProfessor(String pname, int page, String ptel, String plab) {
		Connection conn = DatabaseUtil.getConnection();	
		String SQL = "insert into professor VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, String.valueOf(getNextPid()));
			pstmt.setString(2, pname);
			pstmt.setInt(3, page);
			pstmt.setString(4, ptel);
			pstmt.setString(5, plab);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//교수님 검색 메소드
	public void searchProfessor(String pname) {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select * from professor where pname LIKE ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + pname + "%");
			rs = pstmt.executeQuery();
			System.out.println("교수번호"+" / "+"이름"+" / "+"나이"+" / "+"전화번호" + " / " + "연구실");
			while(rs.next()) {
				System.out.println(rs.getString(1)+"    "+rs.getString(2)+" "+rs.getInt(3)+
						"  세 " + rs.getString(4)  +"   "+ rs.getString(5) + "호");
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//교수님 정보 삭제 메소드
	public void deleteProfessor(String pid) {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "delete from professor where pid = ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, pid);
			pstmt.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
