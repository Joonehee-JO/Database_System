package lead;

import java.sql.*;
import util.DatabaseUtil;

public class LeadDAO {
	ResultSet rs; 
	
	//지도교수가 없는 학생들 출력
	public void searchStudentNotLeadding() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select sid, sname from student where student.sid NOT IN (select student.sid from student,lead where student.sid = lead.sid)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("학번"+" / "+"이름");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//교수별 지도학생 수 출력
	public void CALCULATING_NUMBER_LEADING_STUDENTS() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select pname, count(*) from professor,lead where professor.pid = lead.pid GROUP BY professor.pid";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("교수명"+" / "+"지도학생 수");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getInt(2));
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//학생 - 지도교수 연결
	public void mappingLeading(String sid, String pid, Date lday) {
		Connection conn = DatabaseUtil.getConnection();	
		String SQL = "insert into lead VALUES(?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, pid);
			pstmt.setString(2, sid);
			pstmt.setDate(3, lday);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//한 학생은 한명의 지도교수로만 매핑됨
	public boolean checkDuplicatelead(String sid) {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select * from lead where sid = ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			pstmt.setString(1, sid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			else return false;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//한명의 교수 당 50명의 학생만 지도가능을 체크하기 위한 메소드
	public boolean checkLimitLeadStudent(String pid) {
		try {
			int num = 0;
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select count(*) from lead where pid = ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			pstmt.setString(1, pid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				num = rs.getInt(1);
			}
			if(num >= 50)return true;
			else return false;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//모든 지도 관계 출력(교수 번호순 정렬)
	public void selectAllLead() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select professor.pname,student.sname from professor,student,lead where professor.pid=lead.pid AND student.sid = lead.sid ORDER BY professor.pid";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("지도교수"+" / "+"학생이름");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
