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
			System.out.println("원하시는 메뉴얼을 선택하세요 : \n");		//메뉴얼 노출
			System.out.println("1. 교과목관리\t2. 학생관리\t3. 교수관리\t4. 전체보기\t0. 종료");

			input = scanner.nextInt();	//사용자 메뉴얼번호 선택
			
			//교과목 개설 메뉴얼 선택 시 
			if(input == 1) {	
				do {
					System.out.println("교과목 개설 메뉴얼입니다\n");		
					System.out.println("1. 교과목 개설하기\t2. 현재 개설된 교과목 확인하기\t3. 전체 교과목 리스트 출력\t4. 교과목검색\t0. 뒤로가기");
					input = scanner.nextInt();	
					if(input == 3) {
						CDAO.selectAllcourselist();
					}
					else if(input == 4) {
						System.out.println("찾으시는 과목 이름을 입력해주세요");	
						inputstr = scanner.next();
						CDAO.searchCourselist(inputstr);
					}
					else System.out.println("똑바로 입력해주세요.");

				}while(input != 0);
			}
			
			//학생관리 메뉴얼
			else if(input == 2) {
				do {
					System.out.println("학생관리 메뉴얼입니다\n");		
					System.out.println("1. 모든 학생 정보 출력\t2. 학생 정보 입력\t3. 학생 검색\t4. 지도교수가 없는 학생 출력\t5. 학생-지도교수 관계 출력\t6. 교수 별 지도학생 수 출력\t7. 학생-지도교수 연결"
							+ "\t 8. 학생삭제" + "\t 0. 뒤로가기");
					input = scanner.nextInt();	
					if(input == 1) {
						SDAO.selectAllStudent();
					}
					else if(input == 2) {	
						System.out.println("신규 학생을 등록합니다");
						System.out.println("입학년도를 입력해주세요");
						String input_year= scanner.next();
						input_year = mkRandnum(input_year);
						System.out.println(input_year);
						System.out.println("이름을 입력해주세요");
						String input_name = scanner.next();
						System.out.println("나이를 입력해주세요");
						int input_age = scanner.nextInt();
						System.out.println("학년을 입력해주세요");
						int input_grade = scanner.nextInt();
						System.out.println("번호를 입력해주세요");
						String input_tel = scanner.next();
						
						SDAO.insertStudent(input_year, input_name, input_age, input_grade, input_tel);
					}
					else if(input == 3) {
						System.out.println("검색할 학생의 학번을 입력해주세요");
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
						System.out.println("선정할 학생의 학번을 입력해주세요");
						String input_sid= scanner.next();
						if(LDAO.checkDuplicatelead(input_sid)) {
							System.out.println("해당 학생은 이미 지도교수가 존재합니다.");
						}
						else {
							System.out.println("선정할 교수의 번호를 입력해주세요");
							String input_pid= scanner.next();
							if(LDAO.checkLimitLeadStudent(input_pid)) {
								System.out.println("현재 지도학생이 50명을 초과합니다.");
							}
							else {
								java.util.Date utilDate = new java.util.Date();
								java.sql.Date date = new java.sql.Date(utilDate.getTime());
								LDAO.mappingLeading(input_sid, input_pid, date);
							}
						}
					}
					else if(input == 8) {
						System.out.println("삭제할 학생의 학번을 입력해주세요");
						String input_sid = scanner.next();
						SDAO.deleteStudent(input_sid);
					}
					else System.out.println("똑바로 입력해주세요.");

				}while(input != 0);
			}
			
			//교수관리 메뉴얼
			else if(input == 3) {
				do {
					System.out.println("교수관리 메뉴얼입니다\n");		
					System.out.println("1. 모든 교수 정보 출력\t2. 교수 정보 입력\t3. 교수 검색\t4. 교수 삭제");
					input = scanner.nextInt();	
					if(input == 1) {
						PDAO.selectAllProfessor();
					}
					else if(input == 2) {	
						System.out.println("신규 교수님을 등록합니다");
						System.out.println("이름을 입력해주세요");
						String input_name = scanner.next();
						System.out.println("나이를 입력해주세요");
						int input_age = scanner.nextInt();
						System.out.println("전화번호를 입력해주세요");
						String input_tel = scanner.next();
						System.out.println("연구실을 배정해주세요");
						String input_lab = scanner.next();
						
						PDAO.insertProfessor(input_name, input_age, input_tel, input_lab);
					}
					else if(input == 3) {
						System.out.println("검색할 교수님의 이름을 입력해주세요");
						String input_name = scanner.next();
						PDAO.searchProfessor(input_name);
					}
					else if(input == 4) {
						System.out.println("삭제할 교수님의 교수번호를 입력해주세요");
						String input_id = scanner.next();
						PDAO.deleteProfessor(input_id);
					}
					else System.out.println("똑바로 입력해주세요.");

				}while(input != 0);
			}
			
			//종료
			else if(input == 0) break;
			
			//입력 오류 시
			else {
				System.out.println("똑바로 입력해주세요.");
			}
		}
	}
	
	//랜덤 학번 받아오기 메소드
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
