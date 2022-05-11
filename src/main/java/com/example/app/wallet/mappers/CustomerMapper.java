package com.example.app.wallet.mappers;

import com.example.app.wallet.dto.CustomerDTO;
import com.example.app.wallet.model.Customer;
import org.mapstruct.Mapper;


@Mapper
public interface CustomerMapper {
    Customer mapFromCustomerDTOtoCustomer(CustomerDTO dto);

    CustomerDTO mapFromCustomertoCustomerDTO(Customer customer);
}
