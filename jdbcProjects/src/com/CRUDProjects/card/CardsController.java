package com.CRUDProjects.card;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CardsController {
	static Scanner sc = new Scanner(System.in);
    static CardsService cardsService = new CardsService();

    // 계좌 관련 메뉴 표시
    public void showCardMenu() {
        while (true) {
            System.out.println();
            System.out.println("====================== 카드 관리 Menu =======================");
            System.out.println("1.모든 카드 조회 | 2.카드 추가 | 3.특정 카드 조회 | 4.카드 한도 변경");
            System.out.println("5.카드 삭제 | 0.메인 메뉴");
            System.out.println("===========================================================");
            System.out.print("작업선택>>");

            int selectMenu = Integer.parseInt(sc.nextLine());

            switch (selectMenu) {
                case 1 -> f_selectAll();
                case 2 -> f_insert();
                case 3 -> f_selectTarget();
                case 4 -> f_updateLimit();
                case 5 -> f_delete();
                case 0 -> {
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    return;
                }
                default -> System.out.println("작업선택 오류. 다시선택 하세요.");
            }
        }
    }
    
 // 모든 카드 조회 메서드
    private void f_selectAll() {
        List<CardsDTO> cardsList = cardsService.getAllCards();
        CardsView.display(cardsList);
    }

    // 카드 추가 메서드
    private void f_insert() {
        CardsDTO newCard = f_makeHelp();
        if (newCard == null) {
            CardsView.display("카드 추가 실패: 잘못된 입력입니다.");
            return;
        }

        boolean result = cardsService.addCard(newCard);
        CardsView.display(result ? "1건 입력됨" : "입력 실패");
    }

    // 특정 카드 조회 메서드
    private void f_selectTarget() {
        try {
            System.out.print("조회할 카드 ID 입력: ");
            int cardId = Integer.parseInt(sc.nextLine());

            CardsDTO card = cardsService.getCardById(cardId);
            CardsView.display(card);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력: 숫자를 입력하세요.");
        }
    }

    // 카드 한도 수정 메서드
    private void f_updateLimit() {
        try {
            System.out.print("수정할 카드 ID 입력: ");
            int cardId = Integer.parseInt(sc.nextLine());
            System.out.print("새 한도 입력: ");
            double newLimit = Double.parseDouble(sc.nextLine());

            boolean result = cardsService.updateCardLimit(cardId, newLimit);
            CardsView.display(result ? "카드 한도 수정 성공" : "카드 한도 수정 실패");
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력: 숫자를 입력하세요.");
        }
    }

    // 카드 삭제 메서드
    private void f_delete() {
        try {
            System.out.print("삭제할 카드 ID 입력: ");
            int cardId = Integer.parseInt(sc.nextLine());

            boolean result = cardsService.deleteCard(cardId);
            CardsView.display(result ? "카드 삭제 성공" : "카드 삭제 실패");
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력: 숫자를 입력하세요.");
        }
    }

    private CardsDTO f_makeHelp() {
        try {
            System.out.print("1.고객 ID 입력>> ");
            int customerId = Integer.parseInt(sc.nextLine());

            System.out.print("2.계좌 ID 입력>> ");
            int accountId = Integer.parseInt(sc.nextLine());

            System.out.print("3.카드 유형 입력(예: Credit/Debit)>> ");
            String cardType = sc.nextLine().toUpperCase();

            System.out.print("4.카드 한도 입력>> ");
            double limitAmount = Double.parseDouble(sc.nextLine());

            System.out.print("5.카드 상태 입력(예: active/inactive)>> ");
            String status = sc.nextLine().toUpperCase();

            System.out.print("6.카드 PIN 입력>> ");
            String pin = sc.nextLine();

            String cardNumber = generateCardNumber();

            // issuedDate와 expiryDate를 null로 설정하여, DB에서 자동으로 설정되도록 함
            return CardsDTO.builder()
                    .customerId(customerId)
                    .accountId(accountId)
                    .cardType(cardType)
                    .limitAmount(limitAmount)
                    .status(status)
                    .cardNumber(cardNumber)
                    .issuedDate(null) // 자동으로 입력되도록 null 설정
                    .expiryDate(null) // 자동으로 입력되도록 null 설정
                    .pin(pin)
                    .build();

        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력: 숫자를 입력하세요.");
        } catch (Exception e) {
            System.out.println("입력 오류: " + e.getMessage());
        }
        return null;
    }

    private String generateCardNumber() {
        // 간단한 카드 번호 생성기 (16자리)
        StringBuilder cardNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }
    
    
}
