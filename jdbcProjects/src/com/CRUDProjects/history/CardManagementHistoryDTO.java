package com.CRUDProjects.history;

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
public class CardManagementHistoryDTO {
    int historyId;
    int cardId;
    String actionType;
    String oldValue;
    String newValue;
    Date actionDate;
    String description;
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("카드 관리 기록 번호= ").append(historyId).append(", 카드 아이디= ").append(cardId)
				.append(", 카드 상태 변경 타입= ").append(actionType).append(", 이전 정보= ").append(oldValue).append(", 현재 변경된 정보= ")
				.append(newValue).append(", 변경 날짜= ").append(actionDate).append(", 추가 설명=")
				.append(description);
		return builder.toString();
	}
    
}
