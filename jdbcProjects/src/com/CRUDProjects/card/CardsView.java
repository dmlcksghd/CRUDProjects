package com.CRUDProjects.card;

import java.util.List;

public class CardsView {

    // 모든 카드 목록을 표시하는 메서드
    public static void display(List<CardsDTO> cardsList) {
        System.out.println("==== 모든 카드 조회 ====");
        if (cardsList.isEmpty()) {
            System.out.println("카드 데이터가 없습니다.");
            return;
        }

        for (CardsDTO card : cardsList) {
            System.out.println(card);
        }
    }

    // 특정 카드 정보를 표시하는 메서드
    public static void display(CardsDTO card) {
        System.out.println("==== 카드 1건 조회 ====");
        if (card == null) {
            System.out.println("해당 카드 정보가 없습니다.");
        } else {
            System.out.println(card);
        }
    }

    // 메시지를 표시하는 메서드
    public static void display(String message) {
        System.out.println("[알림] " + message);
    }
}
