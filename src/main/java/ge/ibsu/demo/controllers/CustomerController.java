package ge.ibsu.demo.controllers;

import ge.ibsu.demo.DTO.addCustomerDTO;
import ge.ibsu.demo.entity.Customer;
import ge.ibsu.demo.repository.CustomerRepository;
import ge.ibsu.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ResponseBody

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Customer> getAll() {
        return customerService.getAllCustomer();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCostumer(id);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Customer addCustomer(@RequestBody addCustomerDTO rd) {
        return customerService.addCustomer(rd);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Customer editCustomer(@PathVariable Long id, @RequestBody addCustomerDTO addCustomerDTO) throws Exception {
            return customerService.editCustomer(id,addCustomerDTO);
    }

}
