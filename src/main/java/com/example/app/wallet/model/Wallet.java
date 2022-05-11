package com.example.app.wallet.model;

import com.example.app.wallet.model.enumeration.Currency;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet")
/** default olarak Wallet in capital olarak başlangıç harfi default küçük oluyo
// ancak bunu table annotation ı ile istediğimiz gibi olmasını sağlarız - biz de küçük w ile başlattığımız
için şu anlık değişen bir durum yok */
public class Wallet extends AbstractBaseEntity{

    private double balance;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    private LocalDate createDate;


    @JsonBackReference
    @ManyToOne
    Customer customer;


}
