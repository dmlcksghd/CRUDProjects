package com.CRUDProjects.transaction;

import java.util.List;

import com.CRUDProjects.card.CardsDTO;
//
public class TransactionView {

	// 모든 거래 목록을 표시하는 메서드
    public static void display(List<TransactionsDTO> transactionsList) {
        System.out.println("==== 모든 거래 조회 ====");
        if (transactionsList.isEmpty()) {
            System.out.println("거래 데이터가 없습니다.");
            return;
        }

        for (TransactionsDTO transaction : transactionsList) {
            System.out.println(transaction);
        }
    }

    // 특정 거래 정보를 표시하는 메서드
    public static void display(TransactionsDTO transaction) {
        System.out.println("==== 거래 1건 조회 ====");
        if (transaction == null) {
            System.out.println("해당 거래 정보가 없습니다.");
        } else {
            System.out.println(transaction);
        }
    }

    // 메시지를 표시하는 메서드
    public static void display(String message) {
        System.out.println("[알림] " + message);
    }

}
