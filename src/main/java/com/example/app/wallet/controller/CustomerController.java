package com.example.app.wallet.controller;

import com.example.app.wallet.dto.CustomerDTO;
import com.example.app.wallet.model.Customer;
import com.example.app.wallet.service.CustomerService;
import com.example.app.wallet.util.ClientRequestInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Slf4j //configure edilmiş bir logger veriyor 5.1.2 - 1.00
public class CustomerController {

    private final CustomerService customerService;
    private final ClientRequestInfo clientRequestInfo;

    @PostMapping("/save-customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid CustomerDTO customerdto) {
        //log.info(String.valueOf(clientRequestInfo));
        Optional<Customer> resultOptional = customerService.saveCustomer(customerdto);
        if(resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
