package com.CRUDProjects.main;

import java.util.Scanner;

import com.CRUDProjects.account.AccountsController;
import com.CRUDProjects.card.CardsController;
import com.CRUDProjects.customer.CustomerController;
import com.CRUDProjects.history.CardHistoryController;
import com.CRUDProjects.transaction.TransactionsController;
//
public class CRUDController {

	private CustomerController customerController = new CustomerController();
	private AccountsController accountsController = new AccountsController();
	private CardsController cardsController = new CardsController();
	private TransactionsController transactionsController = new TransactionsController();
	private CardHistoryController historyController = new CardHistoryController();
	
	
	public static void main(String[] args) {
		CRUDController controller = new CRUDController();
		controller.menuMain();
	}


	private void menuMain() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("================================= Main Menu ===============================");
    		System.out.println("1.고객 관리 | 2.계좌 관리 | 3.카드 관리 | 4.거래정보관리 | 5.카드 변경사항 기록 | 0.종료");
    		System.out.println("===========================================================================");
    		System.out.print("작업선택>>");
    		
    		int selectMenu = Integer.parseInt(sc.nextLine());
    		
    		switch (selectMenu) {
    		case 1 -> { customerController.showCustomerMenu(); }
    		case 2 -> { accountsController.showAccountsMenu(); }
    		case 3 -> { cardsController.showCardMenu(); }
    		case 4 -> { transactionsController.showTransactionMenu(); }
    		case 5 -> { historyController.showHistoryMenu(); }
    		case 0 -> { 
    			System.out.println("**프로그램 종료**"); 
    			sc.close();
    			System.exit(0);
    			}
    		default -> { System.out.println("작업선택 오류. 다시선택 하세요."); }
    		}
		}
	}
	
	

	 

}
