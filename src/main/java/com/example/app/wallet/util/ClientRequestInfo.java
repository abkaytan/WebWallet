package com.example.app.wallet.util;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@NoArgsConstructor
@Component //bu annotation ile aplication contex kapsamında tanımlanmasını container tarafında bind edilmesini vs...
@SessionScope // uygulamada session içersinde bu nesneye eriştiğimiz an o anki güncel haline erişebilmeyi
// sağlar kısacası nesnelerin statelerinin tutulmasını sağlar
public class ClientRequestInfo {

    private String clientIpAddress;
    private String clientUrl;
    private String sessionActivityId;

}
