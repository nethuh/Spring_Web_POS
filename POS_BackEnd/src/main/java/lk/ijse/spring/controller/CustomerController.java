package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseUtil addCustomer(CustomerDTO dto) {
        service.addCustomer(dto);
        return new ResponseUtil("Ok", "successfully added", dto);
    }

    @PutMapping
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO dto) {
        service.updateCustomer(dto);
        return new ResponseUtil("Ok", "successfully updated", dto);
    }

    @DeleteMapping
    public ResponseUtil deleteCustomer(String id) {
        service.deleteCustomer(id);
        return new ResponseUtil("Ok", "successfully deleted", id);
    }

    @GetMapping
    public ResponseUtil getAllCustomers() {
        return new ResponseUtil("Ok", "successfully loaded", service.getAllCustomers());
    }

    @GetMapping(params = {"id"})
    public ResponseUtil findCustomer(String id) {
        return new ResponseUtil("Ok", "successfully searched", service.findCustomer(id));
    }
}
