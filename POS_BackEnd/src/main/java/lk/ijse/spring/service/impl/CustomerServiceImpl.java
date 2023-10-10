package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerRepo repo;

    @Override
    public void addCustomer(CustomerDTO dto) {
        if (repo.existsById(dto.getId())) throw new RuntimeException("Error, " + dto.getId() + " is already added!");
        repo.save(mapper.map(dto, Customer.class));
    }

    @Override
    public void updateCustomer(CustomerDTO dto) {
        if (!repo.existsById(dto.getId())) throw new RuntimeException("Error, customer is not exists!");
        repo.save(mapper.map(dto, Customer.class));
    }

    @Override
    public void deleteCustomer(String id) {
        if (!repo.existsById(id)) throw new RuntimeException("Error, customer is not exists!");
        repo.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<CustomerDTO>>() {
        }.getType());
    }

    @Override
    public CustomerDTO findCustomer(String id) {
        if (!repo.existsById(id)) throw new RuntimeException("Error, customer is not exists!");
        return mapper.map(repo.findById(id), CustomerDTO.class);
    }
}
