package com.CRUDProjects.history;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.CRUDProjects.util.DBUtil;
//
//DAO(Data Access Object)
//Service---->DB에간다. 
//       <--- 
public class CardManagementHistoryDAO {
	
	
	 // 모든 이력 조회 메서드
    public List<CardManagementHistoryDTO> selectAllHistory() {
        String sql = "SELECT * FROM CARD_MANAGEMENT_HISTORY";
        List<CardManagementHistoryDTO> historyList = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                historyList.add(makeHistory(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return historyList;
    }

    // 특정 카드의 모든 이력 조회 메서드
    public List<CardManagementHistoryDTO> getHistoryByCardId(int cardId) {
        String sql = "SELECT * FROM CARD_MANAGEMENT_HISTORY WHERE card_id = ?";
        List<CardManagementHistoryDTO> historyList = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cardId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    historyList.add(makeHistory(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return historyList;
    }

    // Helper 메서드: ResultSet을 DTO로 변환
    private CardManagementHistoryDTO makeHistory(ResultSet rs) throws SQLException {
        return CardManagementHistoryDTO.builder()
                .historyId(rs.getInt("history_id"))
                .cardId(rs.getInt("card_id"))
                .actionType(rs.getString("action_type"))
                .oldValue(rs.getString("old_value"))
                .newValue(rs.getString("new_value"))
                .actionDate(rs.getDate("action_date"))
                .description(rs.getString("description"))
                .build();
    }

}
