package com.CRUDProjects.account;

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
public class AccountsDAO {
	
	
	// 모든 계좌 조회
    public List<AccountsDTO> selectAllAccounts() {
        String sql = "SELECT * FROM ACCOUNTS";
        List<AccountsDTO> accountsList = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                accountsList.add(makeAccount(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountsList;
    }

    // 계좌 삽입
    public boolean insertAccount(AccountsDTO account) {
        String sql = "INSERT INTO ACCOUNTS (account_id, customer_id, account_type, balance, created_at, status) VALUES (ACCOUNT_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, account.getCustomerId());
            pstmt.setString(2, account.getAccountType());
            pstmt.setDouble(3, account.getBalance());
            pstmt.setDate(4, account.getCreatedAt());
            pstmt.setString(5, account.getStatus());

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 특정 계좌 조회
    public AccountsDTO getAccountById(int accountId) {
        String sql = "SELECT * FROM ACCOUNTS WHERE account_id = ?";
        AccountsDTO account = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                account = makeAccount(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    // 계좌 상태 업데이트
    public boolean updateAccountStatus(int accountId, String status) {
        String sql = "UPDATE ACCOUNTS SET status = ? WHERE account_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, accountId);

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 계좌 삭제
    public boolean deleteAccount(int accountId) {
        String sql = "DELETE FROM ACCOUNTS WHERE account_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, accountId);

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper 메서드
    private AccountsDTO makeAccount(ResultSet rs) throws SQLException {
        return AccountsDTO.builder()
                .accountId(rs.getInt("account_id"))
                .customerId(rs.getInt("customer_id"))
                .accountType(rs.getString("account_type"))
                .balance(rs.getDouble("balance"))
                .createdAt(rs.getDate("created_at"))
                .status(rs.getString("status"))
                .build();
    }

}
