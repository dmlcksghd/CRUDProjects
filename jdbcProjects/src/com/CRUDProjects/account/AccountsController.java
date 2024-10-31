package com.CRUDProjects.account;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.CRUDProjects.util.DateUtil;
//
public class AccountsController {
    static Scanner sc = new Scanner(System.in);
    static AccountsService accountsService = new AccountsService();

    // 계좌 관련 메뉴 표시
    public void showAccountsMenu() {
        while (true) {
            System.out.println();
            System.out.println("====================== 계좌 관리 Menu =======================");
            System.out.println("1.모든 계좌 조회 | 2.계좌 추가 | 3.특정 계좌 조회 | 4.계좌 상태 수정");
            System.out.println("5.계좌 삭제 | 0.메인 메뉴");
            System.out.println("===========================================================");
            System.out.print("작업선택>>");

            int selectMenu = Integer.parseInt(sc.nextLine());

            switch (selectMenu) {
                case 1 -> f_selectAll();
                case 2 -> f_insert();
                case 3 -> f_selectTarget();
                case 4 -> f_updateStatus();
                case 5 -> f_delete();
                case 0 -> {
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    return;
                }
                default -> System.out.println("작업선택 오류. 다시선택 하세요.");
            }
        }
    }

    // 모든 계좌 조회 메서드
    private void f_selectAll() {
        List<AccountsDTO> accountsList = accountsService.getAllaccounts();
        AccountsView.display(accountsList);
    }

    // 계좌 추가 메서드
    private void f_insert() {
        AccountsDTO newAccount = f_makeHelp();
        if (newAccount == null) {
            AccountsView.display("계좌 추가 실패: 잘못된 입력입니다.");
            return;
        }

        boolean result = accountsService.addAccount(newAccount);
        AccountsView.display(result ? "1건 입력됨" : "입력 실패");
    }

    // 특정 계좌 조회 메서드
    private void f_selectTarget() {
        try {
            System.out.print("조회할 계좌 ID 입력: ");
            int accountId = Integer.parseInt(sc.nextLine());

            AccountsDTO account = accountsService.getAccountById(accountId);
            AccountsView.display(account);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력: 숫자를 입력하세요.");
        }
    }

    // 계좌 상태 수정 메서드
    private void f_updateStatus() {
        try {
            System.out.print("수정할 계좌 번호 입력: ");
            int accountId = Integer.parseInt(sc.nextLine());
            System.out.print("새 상태 입력(예: active/inactive): ");
            String newStatusInput = sc.nextLine().toLowerCase(); // 상태 값을 소문자로 변환하여 입력 받음

            String newStatus;
            if ("active".equals(newStatusInput)) {
                newStatus = "ACTIVE";
            } else if ("inactive".equals(newStatusInput)) {
                newStatus = "INACTIVE";
            } else {
                System.out.println("잘못된 상태 값입니다. 'active' 또는 'inactive' 중 하나를 입력하세요.");
                return;
            }

            boolean result = accountsService.updateAccountStatus(accountId, newStatus);
            AccountsView.display(result ? "계좌 상태 수정 성공" : "계좌 상태 수정 실패");
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력: 숫자를 입력하세요.");
        }
    }


    // 계좌 삭제 메서드
    private void f_delete() {
        try {
            System.out.print("삭제할 계좌 번호 입력: ");
            int accountId = Integer.parseInt(sc.nextLine());

            boolean result = accountsService.deleteAccount(accountId);
            AccountsView.display(result ? "계좌 삭제 성공" : "계좌 삭제 실패");
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력: 숫자를 입력하세요.");
        }
    }

    private AccountsDTO f_makeHelp() {
        try {
            System.out.print("1.고객 ID 입력>> ");
            int customerId = Integer.parseInt(sc.nextLine());

            System.out.print("2.계좌 유형 입력(예: Saving/Checking)>> ");
            String accountTypeTemp = sc.nextLine();
            String accountType = accountTypeTemp.substring(0,1).toUpperCase() + accountTypeTemp.substring(1);

            System.out.print("3.계좌 잔액 입력>> ");
            double balance = Double.parseDouble(sc.nextLine());

            System.out.print("4.계좌 상태 입력(예: active/inactive)>> ");
            String status = sc.nextLine().toUpperCase(); // 대문자로 변환

            // createdAt을 null로 설정하여, DB에서 기본값을 사용하도록 함
            return AccountsDTO.builder()
                    .customerId(customerId)
                    .accountType(accountType)
                    .balance(balance)
                    .createdAt(null) // createdAt을 null로 설정
                    .status(status)
                    .build();

        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력: 숫자를 입력하세요.");
        } catch (Exception e) {
            System.out.println("입력 오류: " + e.getMessage());
        }
        return null;
    }
}
