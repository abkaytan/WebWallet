package com.example.app.wallet.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    // @apimodel property annotation u swagger ile beraber gelen ve id in görünmesini true diye engelledik
    // ayrıca default olarak false olduğunu bilmek gerek, diğerlerine belirli ifade atamaları yapıldı
    @ApiModelProperty(hidden = true)
    private long id;

    @ApiModelProperty(example = "Ali")
    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @ApiModelProperty(example = "Kaytan")
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @ApiModelProperty(example = "23423423")
    @NotNull(message = "Ssid mandatory")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private long ssid;

    @ApiModelProperty(example = "abk@gmaoil.com")
    @Email(message = "Email format is wrong")
    @NotBlank(message = "Email is mandatory")
    private String email;

}
