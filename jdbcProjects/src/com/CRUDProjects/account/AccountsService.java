package com.CRUDProjects.account;

import java.util.List;

//사용자요청-->Controller--->Service--->DAO--->DB
//DB관련없는 업무로직은 Service에서 수행 
public class AccountsService {

	private AccountsDAO accountsDAO = new AccountsDAO();
	
	// 모든 고객 조회
	public List<AccountsDTO> getAllaccounts() {
		return accountsDAO.selectAllAccounts();
	}
	
	// 계좌 추가
    public boolean addAccount(AccountsDTO account) {
        return accountsDAO.insertAccount(account);
    }

    // 특정 계좌 조회
    public AccountsDTO getAccountById(int accountId) {
        return accountsDAO.getAccountById(accountId); // 이 메서드는 AccountsDAO에서 미리 정의된 것으로 가정
    }

    // 계좌 상태 수정 (예: 비활성화)
    public boolean updateAccountStatus(int accountId, String status) {
        return accountsDAO.updateAccountStatus(accountId, status); // 이 메서드는 AccountsDAO에서 미리 정의된 것으로 가정
    }

    // 계좌 삭제
    public boolean deleteAccount(int accountId) {
        return accountsDAO.deleteAccount(accountId); // 이 메서드는 AccountsDAO에서 미리 정의된 것으로 가정
    }

}







