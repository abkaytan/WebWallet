package com.example.app.wallet.controller;

import com.example.app.wallet.dto.CustomerDTO;
import com.example.app.wallet.model.Customer;
import com.example.app.wallet.service.CustomerService;
import net.bytebuddy.description.ModifierReviewable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// import static org.mockito.Mockito.*; yazarsak aşşağıdaki mockito kodlarını yazmadan algılar.

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) //mockito kullanmayı aktif hale getiriyoruz
class CustomerControllerTest {


    @Mock //customer service gidildiği için mocklayarak taklit ediyoruz
    CustomerService mockcustomerService;

    @InjectMocks // customer controllerı inject ediyoruz
    CustomerController customerController;

    @Test
    void saveCustomer() {

        //GIVEN - BDD -> BEHAVIOUR DRIVEN DEVELOPMENT
        Customer customer = new Customer();
        customer.setSsid(234324);
        Optional<Customer> expected = Optional.of(customer);
        //mockito when -- then yapısı kurulur
        Mockito.when(mockcustomerService.saveCustomer(Mockito.any())).thenReturn(expected);
        //Mockito.any() ile objeyi taklit ediyoruz. değişken taklidi için anyInt veya anyString gibi ifadeler de var.
        //customerSErvice i mock luyoruz çünkü service e gitmememiz lazım , ana amaç saveCustomer ı test etmek,
        // unit testlerin birbirinden bağımsızlığını sağlamak için

        //WHEN

        CustomerDTO dto = new CustomerDTO();
        Customer actual = this.customerController.saveCustomer(dto).getBody(); //dto objesini saveCustomer içine verdikten sonra bu
        // bize ResponseEntity<Customer> dönüyor. ve biz bu ResponseEntity içindeki Customer objesine erişmek için
        // .getBody() metodunu kullanırız

        //actual yazarak Customer objesi oluşturup bu actual objenin içine
        // this.customerController.saveCustomer(dto).getBody() ' den gelen customer objesini atıyoruz
        //expected - actual ilişkisi

        //THEN
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(), actual),
                () -> assertEquals(customer.getSsid(), actual.getSsid())
        );
        //assertAll ile içindeki iki metoddan ilki hata vermiş olsa bile iki testi de gerçekleştirir.
        /*
        assertNotNull(actual);
        assertEquals(expected.get(), actual);

         */
    }

    @Test
    void saveCustomer2() {

        //GIVEN - BDD -> BEHAVIOUR DRIVEN DEVELOPMENT
        Mockito.when(mockcustomerService.saveCustomer(Mockito.any())).thenReturn(Optional.empty());

        //WHEN
        CustomerDTO dto = new CustomerDTO();
        ResponseEntity<Customer> actual = this.customerController.saveCustomer(dto);

        //THEN
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());

    }

}