package com.example.app.wallet.dto;

import com.example.app.wallet.model.AbstractBaseEntity;
import com.example.app.wallet.model.Customer;
import com.example.app.wallet.model.Wallet;
import com.example.app.wallet.model.enumeration.Currency;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDTO extends AbstractBaseEntity {

    @ApiModelProperty(hidden = true)
    private long id;

    @ApiModelProperty(example = "0.0")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double balance;

    @ApiModelProperty(example = "TRY or USD or EUR or GBP")
    @NotNull(message = "Currency is mandatory")
    private Currency currency;

    @NotNull(message = "CustomerId mandatory")
    private int customerId;


}
