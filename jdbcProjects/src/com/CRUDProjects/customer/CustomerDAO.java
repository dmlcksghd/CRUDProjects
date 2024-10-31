package com.CRUDProjects.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.CRUDProjects.account.AccountsDTO;
import com.CRUDProjects.card.CardsDTO;
import com.CRUDProjects.util.DBUtil;

//
//DAO(Data Access Object)
//Service---->DB에간다. 
//       <--- 
public class CustomerDAO {
	// 모든 고객 조회
	public List<CustomerDTO> selectAll() {
		String sql = "SELECT * FROM CUSTOMER_INFO";
		List<CustomerDTO> customerList = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				customerList.add(makeCustomer(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}

	// 고객 삽입
	public boolean insertCustomer(CustomerDTO customer) {
		String sql = "INSERT INTO CUSTOMER_INFO (customer_id, full_name, phone_number, email, address) VALUES (CUSTOMER_ID_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, customer.getFullName());
			pstmt.setString(2, customer.getPhoneNumber());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getAddress());

			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 특정 고객 조회(ID로 조회)
	public CustomerDTO getCustomerById(int customerId, String fullName) {
		String sql;
		boolean searchById = customerId > 0;
		CustomerDTO customer = null;

		if (searchById) {
			sql = "SELECT * FROM CUSTOMER_INFO WHERE customer_id = ?";
		} else {
			sql = "SELECT * FROM CUSTOMER_INFO WHERE full_name = ?";
		}

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			if (searchById) {
				pstmt.setInt(1, customerId);
			} else {
				pstmt.setString(1, fullName);
			}

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				customer = makeCustomer(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	// 고객 이메일 수정
	public boolean updateCustomerEmail(int customerId, String newEmail) {
		String sql = "UPDATE CUSTOMER_INFO SET email = ? WHERE customer_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newEmail);
			pstmt.setInt(2, customerId);

			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 고객 삭제
	public boolean deleteCustomer(int customerId) {
		String sql = "DELETE FROM CUSTOMER_INFO WHERE customer_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, customerId);

			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<CustomerDTO> getCustomerDetails(int customerId, String fullName) {
        String baseSql = """
                SELECT
                    C.CUSTOMER_ID AS CUSTOMER_ID, C.FULL_NAME AS FULL_NAME, C.PHONE_NUMBER, C.EMAIL, C.ADDRESS, C.UPDATED_AT,
                    A.ACCOUNT_ID AS ACCOUNT_ID, A.CUSTOMER_ID AS ACCOUNT_CUSTOMER_ID, A.ACCOUNT_TYPE, A.BALANCE, A.STATUS AS ACCOUNT_STATUS,
                    CD.CARD_ID AS CARD_ID, CD.CARD_NUMBER, CD.CUSTOMER_ID AS CARD_CUSTOMER_ID, CD.ACCOUNT_ID AS CARD_ACCOUNT_ID,
                    CD.CARD_TYPE, CD.EXPIRY_DATE, CD.LIMIT_AMOUNT, CD.STATUS AS CARD_STATUS
                FROM
                    CUSTOMER_INFO C
                    LEFT JOIN ACCOUNTS A ON C.CUSTOMER_ID = A.CUSTOMER_ID
                    LEFT JOIN CARDS CD ON A.ACCOUNT_ID = CD.ACCOUNT_ID
                """;

        String sql = (customerId > 0) ? 
        		baseSql + " WHERE C.CUSTOMER_ID = ?" : 
        		baseSql + " WHERE C.FULL_NAME = ?";

        List<CustomerDTO> customerList = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (customerId > 0) {
                pstmt.setInt(1, customerId);
            } else {
                pstmt.setString(1, fullName);
            }

            ResultSet rs = pstmt.executeQuery();

            Map<Integer, CustomerDTO> customerMap = new HashMap<>();

            while (rs.next()) {
                int custId = rs.getInt("CUSTOMER_ID");

                // 고객 정보 가져오기
                CustomerDTO customer = customerMap.get(custId);
                if (customer == null) {
                    customer = makeCustomer(rs);
                    customer.setAccounts(new ArrayList<>());
                    customer.setCards(new ArrayList<>());
                    customerMap.put(custId, customer);
                }

                // 계좌 정보 가져오기
                int accountId = rs.getInt("ACCOUNT_ID");
                if (accountId != 0) {
                    AccountsDTO account = makeAccount(rs);
                    if (!containsAccount(customer.getAccounts(), account)) {
                        customer.getAccounts().add(account);
                    }

                    // 카드 정보 가져오기
                    int cardId = rs.getInt("CARD_ID");
                    if (cardId != 0) {
                        CardsDTO card = makeCard(rs);
                        if (!containsCard(customer.getCards(), card)) {
                            customer.getCards().add(card);
                        }
                    }
                }
            }

            customerList.addAll(customerMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }


	// Helper 메서드들
    private CustomerDTO makeCustomer(ResultSet rs) throws SQLException {
        return CustomerDTO.builder()
                .customerId(rs.getInt("CUSTOMER_ID"))
                .fullName(rs.getString("FULL_NAME"))
                .phoneNumber(rs.getString("PHONE_NUMBER"))
                .email(rs.getString("EMAIL"))
                .address(rs.getString("ADDRESS"))
                .updatedAt(rs.getDate("UPDATED_AT"))
                .build();
    }

    private AccountsDTO makeAccount(ResultSet rs) throws SQLException {
        return AccountsDTO.builder()
                .accountId(rs.getInt("ACCOUNT_ID"))
                .customerId(rs.getInt("ACCOUNT_CUSTOMER_ID"))
                .accountType(rs.getString("ACCOUNT_TYPE"))
                .balance(rs.getDouble("BALANCE"))
                .status(rs.getString("ACCOUNT_STATUS"))
                .build();
    }

    private CardsDTO makeCard(ResultSet rs) throws SQLException {
        return CardsDTO.builder()
                .cardId(rs.getInt("CARD_ID"))
                .cardNumber(rs.getString("CARD_NUMBER"))
                .customerId(rs.getInt("CARD_CUSTOMER_ID"))
                .accountId(rs.getInt("CARD_ACCOUNT_ID"))
                .cardType(rs.getString("CARD_TYPE"))
                .expiryDate(rs.getDate("EXPIRY_DATE"))
                .limitAmount(rs.getDouble("LIMIT_AMOUNT"))
                .status(rs.getString("CARD_STATUS"))
                .build();
    }

    // 중복 계좌 체크 메서드
    private boolean containsAccount(List<AccountsDTO> accountsList, AccountsDTO account) {
        return accountsList.stream().anyMatch(acc -> acc.getAccountId() == account.getAccountId());
    }

    // 중복 카드 체크 메서드
    private boolean containsCard(List<CardsDTO> cardsList, CardsDTO card) {
        return cardsList.stream().anyMatch(crd -> crd.getCardId() == card.getCardId());
    }
}
