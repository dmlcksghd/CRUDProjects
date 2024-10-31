package com.CRUDProjects.card;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//DTO : Data Transfer Object 
//VO : Value Object 
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardsDTO {
    int cardId;
    String cardNumber;
    int customerId;
    int accountId;
    String cardType;
    Date issuedDate;
    Date expiryDate;
    String status;
    double limitAmount;
    String pin;
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("카드 아이디= ").append(cardId).append(", 카드 번호= ").append(cardNumber)
				.append(", 고객 번호= ").append(customerId).append(", 계좌 번호=").append(accountId)
				.append(", 카드 타입 = ").append(cardType).append(", 카드 발급 날짜= ").append(issuedDate)
				.append(", 카드 만료일= ").append(expiryDate).append(", 데이터 상태(ACTIVE , INACTIVE)=").append(status).append(", 신용카드 한도= ")
				.append(limitAmount).append(", pin= ").append(pin).append("]");
		return builder.toString();
	}
    
}
