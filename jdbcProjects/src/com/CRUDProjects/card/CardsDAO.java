package com.CRUDProjects.card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.CRUDProjects.util.DBUtil;

//DAO(Data Access Object)
//Service---->DB에간다. 
//       <--- 
public class CardsDAO {
	
	
	// 모든 카드 조회
    public List<CardsDTO> selectAllCards() {
        String sql = "SELECT * FROM CARDS";
        List<CardsDTO> cardsList = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                cardsList.add(makeCard(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cardsList;
    }

    // 카드 삽입
    public boolean insertCard(CardsDTO card) {
        String sql = "INSERT INTO CARDS (card_id, card_number, customer_id, account_id, card_type, issued_date, expiry_date, status, limit_amount, pin) VALUES (CARD_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, card.getCardNumber());
            pstmt.setInt(2, card.getCustomerId());
            pstmt.setInt(3, card.getAccountId());
            pstmt.setString(4, card.getCardType());
            pstmt.setDate(5, card.getIssuedDate());
            pstmt.setDate(6, card.getExpiryDate());
            pstmt.setString(7, card.getStatus());
            pstmt.setDouble(8, card.getLimitAmount());
            pstmt.setString(9, card.getPin());

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 특정 카드 조회
    public CardsDTO getCardById(int cardId) {
        String sql = "SELECT * FROM CARDS WHERE card_id = ?";
        CardsDTO card = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cardId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                card = makeCard(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }

    // 카드 상태 업데이트
    public boolean updateCardLimit(int cardId, double newLimit) {
        String sql = "UPDATE CARDS SET limit_amount = ? WHERE card_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newLimit);
            pstmt.setInt(2, cardId);

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 카드 삭제
    public boolean deleteCard(int cardId) {
        String sql = "DELETE FROM CARDS WHERE card_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cardId);

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper 메서드: ResultSet을 DTO로 변환
    private CardsDTO makeCard(ResultSet rs) throws SQLException {
        return CardsDTO.builder()
                .cardId(rs.getInt("card_id"))
                .cardNumber(rs.getString("card_number"))
                .customerId(rs.getInt("customer_id"))
                .accountId(rs.getInt("account_id"))
                .cardType(rs.getString("card_type"))
                .issuedDate(rs.getDate("issued_date"))
                .expiryDate(rs.getDate("expiry_date"))
                .status(rs.getString("status"))
                .limitAmount(rs.getDouble("limit_amount"))
                .pin(rs.getString("pin"))
                .build();
    }

}
