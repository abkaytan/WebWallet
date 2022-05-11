package com.example.app.wallet.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends AbstractBaseEntity{

    private String firstName;
    private String lastName;
    private long ssid;
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Wallet> wallets = new ArrayList<>();



}
