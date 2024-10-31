package com.CRUDProjects.transaction;

import java.util.List;
//
public class TransactionsService {
	private TransactionsDAO transactionsDAO = new TransactionsDAO();

    // 모든 거래 조회
    public List<TransactionsDTO> getAllTransactions() {
        return transactionsDAO.selectAllTransactions();
    }

    // 특정 거래 조회
    public TransactionsDTO getTransactionById(int transactionId) {
        return transactionsDAO.getTransactionById(transactionId); // 이 메서드는 TransactionsDAO에서 미리 정의된 것으로 가정
    }

}
