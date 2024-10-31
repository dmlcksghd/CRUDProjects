package com.CRUDProjects.history;

import java.util.List;
import java.util.Scanner;
//
public class CardHistoryController {
    static Scanner sc = new Scanner(System.in);
    static CardManagementHistoryService cardHistoryService = new CardManagementHistoryService();

    // 카드 변경 이력 관련 메뉴 표시
    public void showHistoryMenu() {
        while (true) {
            System.out.println();
            System.out.println("================== 카드 변경사항 Menu ======================");
            System.out.println("1.모든 카드 변경 이력 조회 | 2.특정 카드 변경 이력 조회 | 0.메인 메뉴");
            System.out.println("=========================================================");
            System.out.print("작업선택>>");

            int selectMenu = Integer.parseInt(sc.nextLine());

            switch (selectMenu) {
                case 1 -> f_selectAllHistories();
                case 2 -> f_selectTargetHistory();
                case 0 -> {
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    return;
                }
                default -> System.out.println("작업선택 오류. 다시선택 하세요.");
            }
        }
    }

    // 모든 카드 변경 이력 조회 메서드
    private void f_selectAllHistories() {
        List<CardManagementHistoryDTO> historyList = cardHistoryService.getAllHistories();
        CardHistoryView.display(historyList);
    }

    // 특정 카드 변경 이력 조회 메서드
    private void f_selectTargetHistory() {
        try {
            System.out.print("조회할 카드 ID 입력: ");
            int cardId = Integer.parseInt(sc.nextLine());

            List<CardManagementHistoryDTO> historyList = cardHistoryService.getHistoryById(cardId);
            CardHistoryView.display(historyList);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력: 숫자를 입력하세요.");
        }
    }
}