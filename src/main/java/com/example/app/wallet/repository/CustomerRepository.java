package com.example.app.wallet.repository;

import com.example.app.wallet.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT " +
            "CASE " +
            " WHEN COUNT(c)>0 " +
            " THEN " +
            "    TRUE " +
            " ELSE " +
            "    FALSE " +
            " END " +
            "FROM Customer c " +
            "WHERE c.ssid = ?1")
    boolean selectExistSsid(long ssid);

    @Query("SELECT " +
            "CASE " +
            " WHEN COUNT(c)>0 " +
            " THEN " +
            "    TRUE " +
            " ELSE " +
            "    FALSE " +
            " END " +
            "FROM Customer c " +
            "WHERE c.email = ?1")
    boolean selectExistEmail(long ssid);

    public 
}
