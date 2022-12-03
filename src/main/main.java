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
			System.out.println("원하시는 메뉴얼을 선택하세요 : \n");		//메뉴얼 노출
			System.out.println("1. 교과목개설하기\t2. 삭제\t3. 검색\t4. 전체보기\t0. 종료");

			input = scanner.nextInt();	//사용자 메뉴얼번호 선택
			
			if(input == 1) {	//교과목 개설 메뉴얼 선택 시 
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
			
			else if(input == 0) break;
			else {
				System.out.println("똑바로 입력해주세요.");
			}
		}		
	}
}
