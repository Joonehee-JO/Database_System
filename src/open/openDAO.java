package open;

import java.sql.*;

import util.DatabaseUtil;

public class openDAO {
	ResultSet rs; 
	
	//���ǽ� Ȯ�� �޼ҵ�
	public void checkEmptyLectureroom() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select lid,did from lectureroom,datecode where (lid,did) NOT IN (SELECT lid,did FROM usedlectureroom)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("���ǽ�"+" / "+"���ð�");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" / "+rs.getString(2));
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//���������� ���� �޼ҵ�
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
	
	//��밭�ǽ� ���� �޼ҵ�
	public void insertUsedlectureroom(String oyear, String cid, String lid, String did) {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "insert into usedlectureroom values (?,?,?,?,?)";
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
	
	//�����Ե� �ð�ǥ ���
	public void checkProfessorTimetable() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select pname, did from professor, usedlectureroom where pid IN (select pid from opensubject, usedlectureroom where usedlectureroom.cid = opensubject.cid AND usedlectureroom.oyear = opensubject.oyear)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("������"+" / "+"���ǽð�");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" / "+rs.getString(2));
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//���� ������ ������ ���� ���
	public void printAllOpenSubject() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select distinct cname,lid,pname from courselist,lectureroom,professor,opensubject where (courselist.cid, lid) IN (select distinct cid,lid from usedlectureroom) AND professor.pid=opensubject.pid";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("�����"+" / "+"���ǽ�" + " / " + "��米����");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" / "+rs.getString(2) + "ȣ / " + rs.getString(3));
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//������ uid��ȣ �Ѱ��ֱ�
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
