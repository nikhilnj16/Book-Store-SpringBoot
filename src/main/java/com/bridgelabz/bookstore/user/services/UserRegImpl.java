package com.bridgelabz.bookstore.user.services;


import com.bridgelabz.bookstore.user.dto.ForgotPasswordDTO;
import com.bridgelabz.bookstore.user.dto.NewPasswordDTO;
import com.bridgelabz.bookstore.user.dto.ResetPasswordDTO;
import com.bridgelabz.bookstore.user.entity.ForgetPassword;
import com.bridgelabz.bookstore.user.repository.ForgetPasswordRepository;
import com.bridgelabz.bookstore.user.utility.EmailSender;
import com.bridgelabz.bookstore.user.utility.UserJwt;
import com.bridgelabz.bookstore.user.dto.UserLoginDTO;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import com.bridgelabz.bookstore.user.repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;


@Service
public class UserRegImpl implements IUserReg {
    @Autowired
    private IUserRepository repo;

    @Autowired
    UserJwt userJwt;

    @Autowired
    EmailSender emailSender;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;

    @Override
    public HashMap<String, String> userRegistration(UserEntity userEntity){
        String encodePassword = passwordEncoder.encode(userEntity.getUserPassword());
        userEntity.setUserPassword(encodePassword);
        userEntity.setUserVerified(false);
        repo.save(userEntity);
        System.out.println(userEntity);
//        String body = "Thanks for registering in Book store application. Please verify your account" ;
//        String subject = "registered successfully and get your account verified";
//        System.out.println(userEntity.getUserEmailId());
//        emailSender.sendEmail(userEntity.getUserEmailId(), subject ,body);

        HashMap<String, String> response = new HashMap<>();
        response.put("Status" , "OK");
        return response;
    }

    @Override
    public HashMap<String, String> userLogin(UserLoginDTO userLoginDto){
//        String encodePassword = passwordEncoder.encode(userLoginDto.getUserPassword());
//        System.out.println(encodePassword);

        UserEntity userEntity = repo.findByEmailId(userLoginDto.getUserEmailId());

        if(userEntity != null && passwordEncoder.matches(userLoginDto.getUserPassword(), userEntity.getUserPassword())) {
            String token = userJwt.createToken(userEntity.getUserFirstName());
            if(userEntity.getUserVerified().equals(false)) {
                String body = "Successfully registered";
                String subject = "account verified";
                emailSender.sendEmail(userLoginDto.getUserEmailId(), subject, body);
                userEntity.setUserVerified(true);
                repo.save(userEntity);
            }
            HashMap<String, String> response = new HashMap<>();
            response.put("Status" , "OK");
            response.put("token", token);
            return response;

        } else {
            return null;
        }
    }


    @Override
    public UserEntity getUser(String token) {
        String userFirstName = userJwt.decodeToken(token);
        System.out.println(repo.findByFirstName(userFirstName));
        return repo.findByFirstName(userFirstName);
    }

    @Override
    public HashMap<String, String> forgetPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO){
        UserEntity userEntity = repo.findByEmailId(forgotPasswordDTO.getUserEmailId());
        int otp = otpGenerator();
        ForgetPassword fp = ForgetPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000))
                .userEntity(userEntity)
                .build();


        if(userEntity != null){
//            String token = userJwt.createResetToken(userEntity.getUserLastName());
            String body = "No need to worry \n you can reset your book store application password by clicking the link : localhost:8085/user/resetPassword \n"
                 + "\n "  + otp +  " Use the token to reset password";
            String subject = "Reset your password";
            emailSender.sendEmail(forgotPasswordDTO.getUserEmailId(), subject, body);

            forgetPasswordRepository.save(fp);

            HashMap<String, String> response = new HashMap<>();
            response.put("Status" , "OK");
            return response;
        } else {
            return null;
        }
    }
    @Override
    public String newPassword(@RequestBody NewPasswordDTO newPasswordDTO){
//        String userLastName = userJwt.decodeResetToken(userPasswordResetDTO.getToken());
        UserEntity userEntity = repo.findByEmailId(newPasswordDTO.getUserEmailId());
        ForgetPassword fp = forgetPasswordRepository.findByOtpAndUser(newPasswordDTO.getOtp(), userEntity);
        if(fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgetPasswordRepository.deleteById(fp.getFpId());
            return "OTP has expired";
        }
        if(fp.getOtp() == null){
            return "Invalid OTP";
        }
        if(userEntity != null) {
            String encodePassword = passwordEncoder.encode(newPasswordDTO.getUserPassword());
            userEntity.setUserPassword(encodePassword);
            System.out.println(encodePassword);
            repo.save(userEntity);
            forgetPasswordRepository.deleteById(fp.getFpId());
            return "Password changed successfully";
        } else {
            return "Invalid Credentials";
        }
    }

    @Override
    public String resetPassword(ResetPasswordDTO resetPasswordDTO) {
        UserEntity userEntity = repo.findByEmailId(resetPasswordDTO.getUserEmailId());
        System.out.println(userEntity);
        if(userEntity != null && passwordEncoder.matches(resetPasswordDTO.getUserOldPassword(), userEntity.getUserPassword())){
            String encodePassword = passwordEncoder.encode(resetPasswordDTO.getUserNewPassword());
            System.out.println(resetPasswordDTO.getUserNewPassword());
            userEntity.setUserPassword(encodePassword);
            repo.save(userEntity);
            return "Password changed successfully";
        }else {
            return "Invalid Credentials";
        }
    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }

    //    @Autowired
//    private IBookStoreRepository repo;
//    public List<UserLoginDto> getAllUserDetails(){
//        return repo.findAll()
//                .stream()
//                .map(userEntity -> new UserLoginDto(
//                        userEntity.getUserId(),
//                        userEntity.getUserFirstName(),
//                        userEntity.getUserLastName()
//
//                )).collect(Collectors.toList());
//    }
//    public List<UserLoginDto> getUserById(@PathVariable int id){
//       return repo.findById(id).stream()
//               .map(userEntity -> new UserLoginDto(
//                       userEntity.getUserId(),
//                       userEntity.getUserFirstName(),
//                       userEntity.getUserLastName()
//
//               )).collect(Collectors.toList());
//       // return repo.fin>dById(id).get();
//    }
//
//    public void postUser(@RequestBody UserEntity userEntity){
//        repo.save(userEntity);
//    }
//
//    public UserEntity putUserDetails(@PathVariable int id){
//        UserEntity userEntity = repo.findById(id).get();
//        userEntity.setUserFirstName("Nikhil");
//        userEntity.setUserLastName("Jana");
//        repo.save(userEntity);
//        return userEntity;
//    }
//    public void deleteUser(@PathVariable int id){
//        UserEntity userEntity = repo.findById(id).get();
//        repo.delete(userEntity);
//    }

}
