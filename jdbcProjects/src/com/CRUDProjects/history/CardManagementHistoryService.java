package com.CRUDProjects.history;

import java.util.List;
//
public class CardManagementHistoryService {
    private CardManagementHistoryDAO historyDAO = new CardManagementHistoryDAO();

    // 모든 카드 관리 이력 조회
    public List<CardManagementHistoryDTO> getAllHistories() {
        return historyDAO.selectAllHistory();
    }

    // 특정 카드의 모든 관리 이력 조회
    public List<CardManagementHistoryDTO> getHistoryById(int cardId) {
        return historyDAO.getHistoryByCardId(cardId); // 해당 메서드는 DAO에서 정의해야 함
    }
}
