package com.example.app.wallet.service;

import com.example.app.wallet.dto.LoginDTO;
import com.example.app.wallet.model.Login;
import com.example.app.wallet.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void save(LoginDTO loginDTO) {
        mapToDTO(this.loginRepository.save(
                new Login(
                    loginDTO.getUsername(),
                    loginDTO.getPassword()
                )
        ));
    }

    public static LoginDTO mapToDTO(Login login) {
        if (login != null) {
            return new LoginDTO(
                    login.getUsername(),
                    login.getPassword()
            );
        }
        return null;
    }
}
