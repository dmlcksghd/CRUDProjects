package com.CRUDProjects.card;

import java.util.List;

public class CardsService {
    private CardsDAO cardsDAO = new CardsDAO();

    // 모든 카드 조회
    public List<CardsDTO> getAllCards() {
        return cardsDAO.selectAllCards();
    }

    // 카드 추가
    public boolean addCard(CardsDTO card) {
        return cardsDAO.insertCard(card);
    }

    // 특정 카드 조회
    public CardsDTO getCardById(int cardId) {
        return cardsDAO.getCardById(cardId);
    }

    // 카드 한도 수정
    public boolean updateCardLimit(int cardId, Double newLimit) {
        return cardsDAO.updateCardLimit(cardId, newLimit);
    }

    // 카드 삭제
    public boolean deleteCard(int cardId) {
        return cardsDAO.deleteCard(cardId);
    }
}
