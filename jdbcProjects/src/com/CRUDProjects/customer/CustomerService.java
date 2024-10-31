package com.CRUDProjects.customer;

import java.util.List;
//
//사용자요청-->Controller--->Service--->DAO--->DB
//DB관련없는 업무로직은 Service에서 수행 
public class CustomerService {

	private CustomerDAO customerDAO = new CustomerDAO();
	
	// 모든 고객 조회
    public List<CustomerDTO> getAllCustomers() {
        return customerDAO.selectAll();
    }

    // 고객 추가
    public boolean addCustomer(CustomerDTO customer) {
        return customerDAO.insertCustomer(customer);
    }

    // 특정 고객 조회
    public CustomerDTO getCustomerById(int customerId, String fullName) {
        return customerDAO.getCustomerById(customerId, fullName);
    }

    // 고객 이메일 수정
    public boolean updateCustomerEmail(int customerId, String newEmail) {
        return customerDAO.updateCustomerEmail(customerId, newEmail);
    }

    // 고객 삭제
    public boolean deleteCustomer(int customerId) {
        return customerDAO.deleteCustomer(customerId);
    }
    
    // 특정 고객 세부정보(계좌ID, 카드ID, 계좌번호, 잔액, 한도) 조회
    public List<CustomerDTO> getCustomerDetails(int customerId, String fullName) {
        return customerDAO.getCustomerDetails(customerId, fullName);
    }

}







