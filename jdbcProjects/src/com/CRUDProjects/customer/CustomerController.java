package com.CRUDProjects.customer;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.CRUDProjects.util.DateUtil;
//
public class CustomerController {
	static Scanner sc = new Scanner(System.in);
	static CustomerService customerService = new CustomerService();
	
	public void showCustomerMenu() {
		
		while(true) {
			System.out.println();
			System.out.println("====================== 고객 관리 Menu ========================");
    		System.out.println("1.모든 고객 조회 | 2.고객 추가 | 3.고객 상세 조회 | 4.이메일 수정");
    		System.out.println("5.고객 삭제 | 6.고객의 카드, 계좌 조회 | 0.메인 메뉴");
    		System.out.println("============================================================");
    		System.out.print("작업선택>>");
    		
    		int selectMenu = Integer.parseInt(sc.nextLine());
    		
    		switch (selectMenu) {
    		case 1 -> { f_select(); }
    		case 2 -> { f_insert(); }
    		case 3 -> { f_selectTarget(); }
    		case 4 -> { f_updateEmail(); }
    		case 5 -> { f_delete(); }
    		case 6 -> { f_selectTotal(); }
    		case 0 -> { 
    			System.out.println("메인 메뉴로 돌아갑니다.");
                return;
    			}
    		default -> { System.out.println("작업선택 오류. 다시선택 하세요."); }
    		}
		}

}

	private void f_selectTotal() {
	    System.out.println("고객 조회를 위한 조건을 선택하세요:");
	    System.out.println("=====================================");
	    System.out.println("1. 고객 ID로 조회 | 2. 고객 이름으로 조회");
	    System.out.println("=====================================");
	    System.out.print("선택 >> ");

	    String selectInput = sc.nextLine();
	    int selectChoice = 0;

	    try {
	        selectChoice = Integer.parseInt(selectInput);
	    } catch (NumberFormatException e) {
	        System.out.println("숫자를 입력해주세요.");
	        return;
	    }

	    int customerId = 0;
	    String fullName = null;

	    switch (selectChoice) {
	        case 1 -> {
	            System.out.print("고객 ID 입력: ");
	            try {
	                customerId = Integer.parseInt(sc.nextLine());
	            } catch (NumberFormatException e) {
	                System.out.println("유효한 숫자를 입력해주세요.");
	                return;
	            }
	        }
	        case 2 -> {
	            System.out.print("고객 이름 입력: ");
	            fullName = sc.nextLine();
	        }
	        default -> {
	            System.out.println("잘못된 선택입니다. 다시 시도하세요.");
	            return;
	        }
	    }

	    List<CustomerDTO> customerList = customerService.getCustomerDetails(customerId, fullName);
	    if (customerList.isEmpty()) {
	        System.out.println("고객 데이터가 없습니다.");
	    } else {
	        CustomerView.displayCustomerAccountsAndCards(customerList);
	    }
	}


	// 고객 삭제 메서드
	private void f_delete() {
		System.out.print("삭제할 고객 ID 입력: ");
        int customerId = Integer.parseInt(sc.nextLine());

        boolean result = customerService.deleteCustomer(customerId);
        CustomerView.display(result ? "고객 삭제 성공" : "고객 삭제 실패");
	}

	//고객 이메일 수정 메서드
	private void f_updateEmail() {
		System.out.print("수정할 고객 ID 입력: ");
        int customerId = Integer.parseInt(sc.nextLine());
        System.out.print("새 이메일 입력: ");
        String newEmail = sc.nextLine();

        boolean result = customerService.updateCustomerEmail(customerId, newEmail);
        CustomerView.display(result ? "이메일 수정 성공" : "이메일 수정 실패");
	}
	
	// 특정 고객 조회 메서드
	private void f_selectTarget() {
		System.out.println("고객 조회를 위한 조건을 선택하세요:");
		System.out.println("=====================================");
	    System.out.println("1. 고객 ID로 조회 | 2. 고객 이름으로 조회");
	    System.out.println("=====================================");
	    System.out.print("선택 >> ");
		
		int selectChoice = Integer.parseInt(sc.nextLine());
		
		int customerId = 0;
		String fullName = null;
		
		switch(selectChoice) {
		case 1 -> { 
			System.out.print("고객 ID 입력: ");
			customerId = Integer.parseInt(sc.nextLine());
			}
		case 2 -> {
			System.out.print("고객 이름 입력: ");
            fullName = sc.nextLine();
		}
		default -> { System.out.println("잘못된 선택입니다. 다시 시도하세요.");
        return; }
		}
		
		CustomerDTO customer = customerService.getCustomerById(customerId, fullName);
		CustomerView.display(customer);
	}

	// 고객 추가 메서드
	private void f_insert() {
		CustomerDTO newCustomer = f_makeHelp();
		if (newCustomer == null) {
			CustomerView.display("고객 추가 실패: 잘못된 입력입니다.");
			return;
		}
		boolean result = customerService.addCustomer(newCustomer);
        CustomerView.display(result ? "1건 입력됨" : "입력 실패");
	}
	
	// 모든 고객 조회 메서드
    private void f_select() {
        List<CustomerDTO> customerList = customerService.getAllCustomers();
        CustomerView.display(customerList);
    }
	
	private CustomerDTO f_makeHelp() {
		try {
            System.out.print("1.고객 ID>> ");
            int customerId = Integer.parseInt(sc.nextLine());

            System.out.print("2.이름 입력>> ");
            String fullName = sc.nextLine();

            System.out.print("3.전화번호 입력>> ");
            String phoneNumber = sc.nextLine();

            System.out.print("4.이메일 입력>> ");
            String email = sc.nextLine();

            System.out.print("5.주소 입력>> ");
            String address = sc.nextLine();

            System.out.print("6.업데이트 날짜(yyyy-MM-dd)>> ");
            String updateDateStr = sc.nextLine();
            Date updatedAt = DateUtil.convertSqlDate(DateUtil.convertDate(updateDateStr));

            return CustomerDTO.builder()
                    .customerId(customerId)
                    .fullName(fullName)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .address(address)
                    .updatedAt(updatedAt)
                    .build();

        } catch (Exception e) {
            System.out.println("입력 오류: " + e.getMessage());
            return null;
        }
	}
	
}
