package main;

import java.sql.*;
import java.util.Scanner;

import util.DatabaseUtil;
import courselist.*;
import java.sql.*;

public class main {
	public static void main(String args[]){		
		Scanner scanner = new Scanner(System.in);	
		int input;
		String inputstr;
		CouselistDAO CDAO = new CouselistDAO();
		
		while(true) {
			System.out.println("���Ͻô� �޴����� �����ϼ��� : \n");		//�޴��� ����
			System.out.println("1. �����񰳼��ϱ�\t2. ����\t3. �˻�\t4. ��ü����\t0. ����");

			input = scanner.nextInt();	//����� �޴����ȣ ����
			
			if(input == 1) {	//������ ���� �޴��� ���� �� 
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
			
			else if(input == 0) break;
			else {
				System.out.println("�ȹٷ� �Է����ּ���.");
			}
		}		
	}
}
