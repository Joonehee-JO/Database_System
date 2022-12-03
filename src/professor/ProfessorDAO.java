package professor;

import java.sql.*;

import util.DatabaseUtil;

public class ProfessorDAO {
	ResultSet rs; 
	
	//������ȣ�� ������ ��ȣ ��������
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
	
	//��� �����Ե� ���� ���
	public void selectAllProfessor() {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select * from professor order by cast(pid as unsigned)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			rs = pstmt.executeQuery();
			System.out.println("������ȣ"+" / "+"�̸�"+" / "+"����"+" / "+"��ȭ��ȣ" + " / " + "������");
			while(rs.next()) {
				System.out.println(rs.getString(1)+"    "+rs.getString(2)+" "+rs.getInt(3)+
						"  �� " + rs.getString(4)  +"   "+ rs.getString(5) + "ȣ");
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//������ �űԵ��
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
	
	//������ �˻� �޼ҵ�
	public void searchProfessor(String pname) {
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String SQL = "select * from professor where pname LIKE ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + pname + "%");
			rs = pstmt.executeQuery();
			System.out.println("������ȣ"+" / "+"�̸�"+" / "+"����"+" / "+"��ȭ��ȣ" + " / " + "������");
			while(rs.next()) {
				System.out.println(rs.getString(1)+"    "+rs.getString(2)+" "+rs.getInt(3)+
						"  �� " + rs.getString(4)  +"   "+ rs.getString(5) + "ȣ");
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//������ ���� ���� �޼ҵ�
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
