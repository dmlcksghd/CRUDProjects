package com.CRUDProjects.account;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountsDTO {
	int accountId;
    int customerId;
    String accountType;
    double balance;
    Date createdAt;
    String status;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("계좌 번호= ").append(accountId).append(", 고객 번호= ").append(customerId)
				.append(", accountType= ").append(accountType).append(", 계좌 잔액= ").append(balance)
				.append(", 마지막 정보 수정 날짜= ").append(createdAt).append(", 계좌 상태(ACTIVE , INACTIVE)= ").append(status);
		return builder.toString();
	}
    
    
}
