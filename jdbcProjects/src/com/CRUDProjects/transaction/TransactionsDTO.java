package com.CRUDProjects.transaction;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionsDTO {
    int transactionId;
    int fromAccountId;
    int toAccountId;
    double amount;
    String transactionType;
    String description;
    Date transactionDate;
    int cardId;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("거래 정보 번호=").append(transactionId).append(", 송금한 사람 번호= ")
				.append(fromAccountId).append(", 입금 받은 사람 번호= ").append(toAccountId).append(", 카드 아이디=").append(cardId)
				.append(", 금액= ").append(amount).append(", 거래 타입= ").append(transactionType).append(", 내용= ")
				.append(description).append(", 거래 날짜= ").append(transactionDate);
		return builder.toString();
	}
    
    
}
