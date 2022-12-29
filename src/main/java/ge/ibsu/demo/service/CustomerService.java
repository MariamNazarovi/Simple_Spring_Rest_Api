package ge.ibsu.demo.service;

import ge.ibsu.demo.DTO.Paging;
import ge.ibsu.demo.DTO.addCustomerDTO;
import ge.ibsu.demo.DTO.SearchCustomer;
import ge.ibsu.demo.entity.Address;
import ge.ibsu.demo.entity.Customer;
import ge.ibsu.demo.repository.AddressRepository;
import ge.ibsu.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer  getCostumer(Long id){
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Customer addCustomer(addCustomerDTO addCustomerDTO){
        Customer customer = new Customer();
        customer.setFirstName(addCustomerDTO.getFirstName());
        customer.setLastName((addCustomerDTO.getLastName()));
        customer.setEmail((addCustomerDTO.getEmail()));
        customer.setCreateDate(new Date());
        customer.setActive(1);

        Address address = new Address();
        address.setAddress(addCustomerDTO.getAddress());
        address = addressRepository.save(address);

        customer.setAddress(address);

        return customerRepository.save(customer);

    }
    @Transactional
    public Customer editCustomer(Long id, addCustomerDTO addCustomerDTO) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("customer_not_found"));
        customer.setFirstName(addCustomerDTO.getFirstName());
        customer.setLastName(addCustomerDTO.getLastName());
        customer.setEmail(addCustomerDTO.getEmail());
        customer.getAddress().setAddress(addCustomerDTO.getAddress());
        return customerRepository.save(customer);
    }

    public Slice<Customer> search(SearchCustomer searchcustomer, Paging paging){
        String searchText = null;
        if (searchcustomer.getSearchText() != null && !searchcustomer.getSearchText().equals("")){
            searchText = "%" + searchcustomer.getSearchText() + "%";
        }
        Pageable pageable = PageRequest.of(paging.getPage()-1,paging.getSize(), Sort.by("createDate").ascending());
        return customerRepository.search(1, searchText, pageable);
    }


}
