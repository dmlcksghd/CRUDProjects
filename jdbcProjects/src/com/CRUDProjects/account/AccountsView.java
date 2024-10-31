package com.CRUDProjects.account;

import java.util.List;

import com.CRUDProjects.customer.CustomerDTO;

public class AccountsView {

	public static void display(List<AccountsDTO> accountsList) {
		System.out.println("====모든 계좌 조회====");

		for (AccountsDTO account : accountsList) {
			System.out.println(account);
		}
	}

	public static void display(AccountsDTO account) {
		System.out.println("====계좌 1건 조회====");
		System.out.println(account == null ? "고객 데이터가 없습니다." : account);
	}

	public static void display(String message) {
		System.out.println("[알림]" + message);

	}

}
