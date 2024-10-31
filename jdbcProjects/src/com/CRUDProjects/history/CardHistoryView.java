package com.CRUDProjects.history;

import java.util.List;
//
public class CardHistoryView {
	public static void display(List<CardManagementHistoryDTO> historyList) {
		System.out.println("====모든 계좌 조회====");

		for (CardManagementHistoryDTO history : historyList) {
			System.out.println(history);
		}
	}

	public static void display(CardManagementHistoryDTO history) {
		System.out.println("====계좌 1건 조회====");
		System.out.println(history == null ? "고객 데이터가 없습니다." : history);

	}

	public static void display(String message) {
		System.out.println("[알림]" + message);

	}
}
