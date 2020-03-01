package com.api.dinnercontest.service;

import com.api.dinnercontest.model.LoginModel;
import com.api.dinnercontest.model.UserTokenModel;
import com.api.dinnercontest.repository.LoginRepository;
import com.api.dinnercontest.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean accessSuccessful(LoginModel loginModel) {
        return BCrypt.checkpw(loginModel.getPassword(), loginRepository.getEncodedPassword(loginModel.getAccessName()));
    }

    public String setToken(Long userId, String accessName) {
        String token = generateToken();
        loginRepository.setToken(userId, accessName, token);
        return token;
    }

    public boolean checkToken(UserTokenModel userTokenModel) {
        return loginRepository.checkToken(userTokenModel) > 0;
    }

    public boolean checkIdToken(Long userId, String token) {
        return loginRepository.checkIdToken(userId, token) > 0;
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

    public Long getUserId(String accessName) {
        return userRepository.findId(accessName);
    }
}
