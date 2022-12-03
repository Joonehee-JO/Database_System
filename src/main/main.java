package main;

import java.sql.*;
import java.util.Scanner;

import util.DatabaseUtil;
import courselist.*;
import lead.*;
import professor.ProfessorDAO;
import student.*;

import java.util.Random;

public class main {
	public static void main(String args[]){		
		Scanner scanner = new Scanner(System.in);	
		int input;
		String inputstr;
		CouselistDAO CDAO = new CouselistDAO();
		StudentDAO SDAO = new StudentDAO();
		LeadDAO LDAO = new LeadDAO();
		ProfessorDAO PDAO = new ProfessorDAO();
		
		while(true) {
			System.out.println("���Ͻô� �޴����� �����ϼ��� : \n");		//�޴��� ����
			System.out.println("1. ���������\t2. �л�����\t3. ��������\t4. ��ü����\t0. ����");

			input = scanner.nextInt();	//����� �޴����ȣ ����
			
			//������ ���� �޴��� ���� �� 
			if(input == 1) {	
				do {
					System.out.println("������ ���� �޴����Դϴ�\n");		
					System.out.println("1. ������ �����ϱ�\t2. ���� ������ ������ Ȯ���ϱ�\t3. ��ü ������ ����Ʈ ���\t4. ������˻�\t0. �ڷΰ���");
					input = scanner.nextInt();	
					if(input == 3) {
						CDAO.selectAllcourselist();
					}
					else if(input == 4) {
						System.out.println("ã���ô� ���� �̸��� �Է����ּ���");	
						inputstr = scanner.next();
						CDAO.searchCourselist(inputstr);
					}
					else System.out.println("�ȹٷ� �Է����ּ���.");

				}while(input != 0);
			}
			
			//�л����� �޴���
			else if(input == 2) {
				do {
					System.out.println("�л����� �޴����Դϴ�\n");		
					System.out.println("1. ��� �л� ���� ���\t2. �л� ���� �Է�\t3. �л� �˻�\t4. ���������� ���� �л� ���\t5. �л�-�������� ���� ���\t6. ���� �� �����л� �� ���\t7. �л�-�������� ����"
							+ "\t 8. �л�����" + "\t 0. �ڷΰ���");
					input = scanner.nextInt();	
					if(input == 1) {
						SDAO.selectAllStudent();
					}
					else if(input == 2) {	
						System.out.println("�ű� �л��� ����մϴ�");
						System.out.println("���г⵵�� �Է����ּ���");
						String input_year= scanner.next();
						input_year = mkRandnum(input_year);
						System.out.println(input_year);
						System.out.println("�̸��� �Է����ּ���");
						String input_name = scanner.next();
						System.out.println("���̸� �Է����ּ���");
						int input_age = scanner.nextInt();
						System.out.println("�г��� �Է����ּ���");
						int input_grade = scanner.nextInt();
						System.out.println("��ȣ�� �Է����ּ���");
						String input_tel = scanner.next();
						
						SDAO.insertStudent(input_year, input_name, input_age, input_grade, input_tel);
					}
					else if(input == 3) {
						System.out.println("�˻��� �л��� �й��� �Է����ּ���");
						String input_id= scanner.next();
						SDAO.searchStudent(input_id);
					}
					else if(input == 4) {
						LDAO.searchStudentNotLeadding();
					}
					else if(input == 5) {
						LDAO.selectAllLead();
					}
					else if(input == 6) {
						LDAO.CALCULATING_NUMBER_LEADING_STUDENTS();
					}
					else if(input == 7) {
						System.out.println("������ �л��� �й��� �Է����ּ���");
						String input_sid= scanner.next();
						if(LDAO.checkDuplicatelead(input_sid)) {
							System.out.println("�ش� �л��� �̹� ���������� �����մϴ�.");
						}
						else {
							System.out.println("������ ������ ��ȣ�� �Է����ּ���");
							String input_pid= scanner.next();
							if(LDAO.checkLimitLeadStudent(input_pid)) {
								System.out.println("���� �����л��� 50���� �ʰ��մϴ�.");
							}
							else {
								java.util.Date utilDate = new java.util.Date();
								java.sql.Date date = new java.sql.Date(utilDate.getTime());
								LDAO.mappingLeading(input_sid, input_pid, date);
							}
						}
					}
					else if(input == 8) {
						System.out.println("������ �л��� �й��� �Է����ּ���");
						String input_sid = scanner.next();
						SDAO.deleteStudent(input_sid);
					}
					else System.out.println("�ȹٷ� �Է����ּ���.");

				}while(input != 0);
			}
			
			//�������� �޴���
			else if(input == 3) {
				do {
					System.out.println("�������� �޴����Դϴ�\n");		
					System.out.println("1. ��� ���� ���� ���\t2. ���� ���� �Է�\t3. ���� �˻�\t4. ���� ����");
					input = scanner.nextInt();	
					if(input == 1) {
						PDAO.selectAllProfessor();
					}
					else if(input == 2) {	
						System.out.println("�ű� �������� ����մϴ�");
						System.out.println("�̸��� �Է����ּ���");
						String input_name = scanner.next();
						System.out.println("���̸� �Է����ּ���");
						int input_age = scanner.nextInt();
						System.out.println("��ȭ��ȣ�� �Է����ּ���");
						String input_tel = scanner.next();
						System.out.println("�������� �������ּ���");
						String input_lab = scanner.next();
						
						PDAO.insertProfessor(input_name, input_age, input_tel, input_lab);
					}
					else if(input == 3) {
						System.out.println("�˻��� �������� �̸��� �Է����ּ���");
						String input_name = scanner.next();
						PDAO.searchProfessor(input_name);
					}
					else if(input == 4) {
						System.out.println("������ �������� ������ȣ�� �Է����ּ���");
						String input_id = scanner.next();
						PDAO.deleteProfessor(input_id);
					}
					else System.out.println("�ȹٷ� �Է����ּ���.");

				}while(input != 0);
			}
			
			//����
			else if(input == 0) break;
			
			//�Է� ���� ��
			else {
				System.out.println("�ȹٷ� �Է����ּ���.");
			}
		}
	}
	
	//���� �й� �޾ƿ��� �޼ҵ�
	public static String mkRandnum(String input_year) {
		String str = input_year;
		StudentDAO SDAO = new StudentDAO();
		
		do {
			Random random = new Random(); 
			random.setSeed(System.currentTimeMillis());
			int num = random.nextInt(130);
			
			str += "038";
			str += String.format("%03d",num);
			
		}while(SDAO.checkDuplicateSid(str));
		
		return str;
	}
}
