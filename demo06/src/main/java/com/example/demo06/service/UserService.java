package com.example.demo06.service;

import com.example.demo06.model.Otp;
import com.example.demo06.model.User;
import com.example.demo06.repo.OtpRepository;
import com.example.demo06.repo.UserRepository;
import com.example.demo06.util.GenerateCodeUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserRepository userRepository;

    @Resource
    private OtpRepository otpRepository;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(User user) {
        Optional<User> optionalUser = userRepository.findUserByUsername(user.getUsername());

        if (optionalUser.isPresent()){
            User u = optionalUser.get();
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                renewOtp(u);
            } else {
                throw new BadCredentialsException("Bad credential!");
            }
        }
    }

    private void renewOtp(User u) {
        String code = GenerateCodeUtil.generateCode();
        Optional<Otp> optionalOtp = otpRepository.findOtpByUsername(u.getUsername());
        if (optionalOtp.isPresent()){
            Otp otp = optionalOtp.get();
            otp.setCode(code);
        } else {
            // 如果该用户名的code不存在，则新增一条记录
            Otp otp = new Otp();
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }

    public boolean check(Otp otp) {
        Optional<Otp> optionalOtp = otpRepository.findOtpByUsername(otp.getUsername());

        if (optionalOtp.isPresent()){
            Otp o = optionalOtp.get();
            if (otp.getCode().equals(o.getCode())) {
                return true;
            }
        }
        return false;
    }
}
