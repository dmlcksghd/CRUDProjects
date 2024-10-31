package com.CRUDProjects.customer;

import java.util.List;

import com.CRUDProjects.account.AccountsDTO;
import com.CRUDProjects.card.CardsDTO;

public class CustomerView {
	public static void display(List<CustomerDTO> customerList) {
		System.out.println("======================모든 고객 조회======================");

		for (CustomerDTO customer : customerList) {
			System.out.println(customer);
		}
	}

	public static void display(CustomerDTO customer) {
		System.out.println("======================고객 1건 조회======================");
		System.out.println(customer == null ? "고객 데이터가 없습니다." : customer);

	}

	public static void display(String message) {
		System.out.println("[알림]" + message);

	}
	
	public static void displayCustomerAccountsAndCards(List<CustomerDTO> customerList) {
	    // 헤더 출력
	    System.out.println("==================================================================================================================");
	    System.out.printf("%-13s %-10s %-12s %-10s %-20s %-15s %-15s\n",
	            "고객 이름", "고객 ID", "계좌 ID", "카드 ID", "카드 번호", "계좌 잔액", "카드 한도");
	    System.out.println("==================================================================================================================");

	    for (CustomerDTO customer : customerList) {
	        List<AccountsDTO> accounts = customer.getAccounts();
	        List<CardsDTO> cards = customer.getCards();

	        if (accounts != null && !accounts.isEmpty()) {
	            for (AccountsDTO account : accounts) {
	                boolean hasCard = false;
	                if (cards != null && !cards.isEmpty()) {
	                    for (CardsDTO card : cards) {
	                        // 계좌 ID를 기준으로 계좌와 카드를 매핑
	                        if (card.getAccountId() == account.getAccountId()) {
	                            hasCard = true;
	                            // 원하는 형식으로 출력
	                            System.out.printf("%-15s %-10d %-12d %-10d %-20s %-15.2f %-15.2f\n",
	                                    customer.getFullName(),
	                                    customer.getCustomerId(),
	                                    account.getAccountId(),
	                                    card.getCardId(),
	                                    card.getCardNumber(),
	                                    account.getBalance(),
	                                    card.getLimitAmount());
	                        }
	                    }
	                }
	                // 해당 계좌에 카드가 없는 경우
	                if (!hasCard) {
	                    System.out.printf("%-15s %-10d %-12d %-10s %-20s %-15.2f %-15s\n",
	                            customer.getFullName(),
	                            customer.getCustomerId(),
	                            account.getAccountId(),
	                            "",     // 카드 ID 없음
	                            "",     // 카드 번호 없음
	                            account.getBalance(),
	                            "");    // 카드 한도 없음
	                }
	            }
	        } else {
	            // 계좌가 없는 경우
	            System.out.printf("%-15s %-10d %-12s %-10s %-20s %-15s %-15s\n",
	                    customer.getFullName(),
	                    customer.getCustomerId(),
	                    "",     // 계좌 ID 없음
	                    "",     // 카드 ID 없음
	                    "",     // 카드 번호 없음
	                    "",     // 계좌 잔액 없음
	                    "");    // 카드 한도 없음
	        }
	    }

	    // 푸터 출력
	    System.out.println("==================================================================================================================");
	}



}
