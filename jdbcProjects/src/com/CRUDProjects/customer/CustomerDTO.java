package com.CRUDProjects.customer;

import java.sql.Date;
import java.util.List;

import com.CRUDProjects.account.AccountsDTO;
import com.CRUDProjects.card.CardsDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
//DTO : Data Transfer Object 
//VO : Value Object 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private int customerId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private Date updatedAt;
    private List<AccountsDTO> accounts;
    private List<CardsDTO> cards;
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("고객 아이디= ").append(customerId).append(", 이름= ").append(fullName)
				.append(", 전화번호= ").append(phoneNumber).append(", 이메일= ").append(email).append(", 주소= ")
				.append(address).append(", 마지막 정보 수정 날짜= ").append(updatedAt);
		return builder.toString();
	}
    
    
	
}