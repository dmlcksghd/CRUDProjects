package com.CRUDProjects.transaction;

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
public class TransactionsDAO {
	
	
	// 모든 거래 조회
    public List<TransactionsDTO> selectAllTransactions() {
        String sql = "SELECT * FROM TRANSACTIONS";
        List<TransactionsDTO> transactionsList = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                transactionsList.add(makeTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionsList;
    }

    // 특정 거래 조회
    public TransactionsDTO getTransactionById(int transactionId) {
        String sql = "SELECT * FROM TRANSACTIONS WHERE transaction_id = ?";
        TransactionsDTO transaction = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transactionId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                transaction = makeTransaction(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    // Helper 메서드: ResultSet을 DTO로 변환
    private TransactionsDTO makeTransaction(ResultSet rs) throws SQLException {
        return TransactionsDTO.builder()
                .transactionId(rs.getInt("transaction_id"))
                .fromAccountId(rs.getInt("from_account_id"))
                .toAccountId(rs.getInt("to_account_id"))
                .amount(rs.getDouble("amount"))
                .transactionType(rs.getString("transaction_type"))
                .description(rs.getString("description"))
                .transactionDate(rs.getDate("transaction_date"))
                .cardId(rs.getInt("card_id"))
                .build();
    }

}
