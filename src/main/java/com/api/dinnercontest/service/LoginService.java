package com.api.dinnercontest.service;

import com.api.dinnercontest.model.LoginModel;
import com.api.dinnercontest.repository.LoginRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean accessSuccesful(LoginModel loginModel) {
        return BCrypt.checkpw(loginModel.getPassword(), loginRepository.getEncodedPassword(loginModel.getAccessName()));
    }

    public String setToken(String accessName) {
        String token = generateToken();
        loginRepository.setToken(accessName, token);
        return token;
    }

    public String generateToken() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 40;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
