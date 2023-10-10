package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
     void addCustomer(CustomerDTO dto);

     void updateCustomer(CustomerDTO dto);

     void deleteCustomer(String id);

     List<CustomerDTO> getAllCustomers();

     CustomerDTO findCustomer(String id);
}
