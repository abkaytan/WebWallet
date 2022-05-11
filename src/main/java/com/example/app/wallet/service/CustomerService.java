package com.example.app.wallet.service;

import com.example.app.wallet.dto.CustomerDTO;
import com.example.app.wallet.exceptions.BadRequestException;
import com.example.app.wallet.mappers.CustomerMapper;
import com.example.app.wallet.model.Customer;
import com.example.app.wallet.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /** aşşağıda tanımlanan constructorın yerine @RequiredArgsConstructor annotation ı ile
     pratik şekilde çözebiliriz.
     */
    /*
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    } */

    /** nullable olması ihtimaline karşı optional içerisine rep ederek de çalışabiliriz.
     Optional; null objeler le çalışmamızı sağlar üzerinde bazı işlemler yapmamızı sağlar
     error durumlarında exception durumlarında onu yönlendirmemizi sağlar
     */
    @Transactional
    public Optional<Customer> saveCustomer(CustomerDTO customerDTO) {

        boolean isExists = customerRepository.selectExistSsid(customerDTO.getSsid());

        if(isExists){
            throw new BadRequestException("Customer with SSID : " + customerDTO.getSsid() + " is already exists!!");
        }

        Customer customer = customerMapper.mapFromCustomerDTOtoCustomer(customerDTO);

        /*
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setSsid(customerDTO.getSsid());
        customer.setEmail(customerDTO.getEmail());

         */
        return Optional.of(customerRepository.save(customer));


    }

    /*
    public Customer saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

     */

}
