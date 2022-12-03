package lead;

import java.sql.*;
import util.DatabaseUtil;

public class LeadDAO {
	ResultSet rs; 
	
	//���������� ���� �л��� ���
	public void searchStudentNotLeadding() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select sid, sname from student where student.sid NOT IN (select student.sid from student,lead where student.sid = lead.sid)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("�й�"+" / "+"�̸�");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//������ �����л� �� ���
	public void CALCULATING_NUMBER_LEADING_STUDENTS() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select pname, count(*) from professor,lead where professor.pid = lead.pid GROUP BY professor.pid";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("������"+" / "+"�����л� ��");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getInt(2));
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//�л� - �������� ����
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
	
	//�� �л��� �Ѹ��� ���������θ� ���ε�
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
	
	//�Ѹ��� ���� �� 50���� �л��� ���������� üũ�ϱ� ���� �޼ҵ�
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
	
	//��� ���� ���� ���(���� ��ȣ�� ����)
	public void selectAllLead() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select professor.pname,student.sname from professor,student,lead where professor.pid=lead.pid AND student.sid = lead.sid ORDER BY professor.pid";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("��������"+" / "+"�л��̸�");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
