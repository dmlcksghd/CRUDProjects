package com.CRUDProjects.transaction;

import java.util.List;
import java.util.Scanner;
//
public class TransactionsController {
	static Scanner sc = new Scanner(System.in);
    static TransactionsService transactionsService = new TransactionsService();
    
    // 트랜잭션 관련 메뉴 표시
    public void showTransactionMenu() {
        while (true) {
            System.out.println();
            System.out.println("================== 거래 정보 Menu ======================");
            System.out.println("1.모든 거래 내역 조회 | 2.특정 거래 내역 조회 | 0.메인 메뉴");
            System.out.println("======================================================");
            System.out.print("작업선택>>");

            int selectMenu = Integer.parseInt(sc.nextLine());

            switch (selectMenu) {
                case 1 -> f_selectAll();
                case 2 -> f_selectTarget();
                case 0 -> {
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    return;
                }
                default -> System.out.println("작업선택 오류. 다시선택 하세요.");
            }
        }
    }
    private void f_selectAll() {
    	List<TransactionsDTO> transactionsList = transactionsService.getAllTransactions();
    	TransactionView.display(transactionsList);
    }
    
    private void f_selectTarget() {
    	try {
    		System.out.print("조회할 거래 ID 입력: ");
            int transactionId = Integer.parseInt(sc.nextLine());
            
            TransactionsDTO transaction = transactionsService.getTransactionById(transactionId);
	        TransactionView.display(transaction);
		} catch (NumberFormatException e) {
			System.out.println("잘못된 입력: 숫자를 입력하세요.");
		}
    	
    }
    
    
}
