package vn.humg.pm.service.Password;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.humg.pm.dto.request.PasswordRequest;
import vn.humg.pm.dto.response.PasswordResponse;
import vn.humg.pm.entity.Password;
import vn.humg.pm.mapping.PasswordMapping;
import vn.humg.pm.repository.PasswordRepository;


@Service
public class PasswordServiceImpl implements PasswordService {

    private final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String num = "0123456789";
    private final String specialChars = "<>,.?/}]{[+_-)(*&^%$#@!=";
    private  Random r = new Random();


    @Autowired
    PasswordRepository passwordRepository;
    private String generatePasswordNoDup(String combination, Integer size){
        char[] password = new char[size];
        for (int i = 0; i < size; i++) {
            password[i] = combination.charAt(r.nextInt(combination.length()));
        }
        
        return new String(password); 
    }
    private String generatePasswordWithDup(String combination, Integer size){
        char[] password = new char[size];
        for (int i = 0; i < size; i++) {
            password[i] = combination.charAt(r.nextInt(combination.length()));
        }

        int index1 = r.nextInt(size);
        int index2;
        do {
            index2 = r.nextInt(size);
        } while (index1 == index2);

        
        password[index2] = password[index1];
        
        return new String(password); 
    }


    @Override
    public PasswordResponse createPassword(PasswordRequest request) {
        if(!request.getPassword().isBlank()){
            throw new RuntimeException("Tạo mật khẩu phải để trống trường mật khẩu");
        }

        Password p=PasswordMapping.toEntity(request);
        StringBuilder sb = new StringBuilder();
        String password;
        String combination;
        if (request.getIsDigits()){
            sb.append(num);
        }

        if(request.getIsLetters()){
            sb.append(chars);
        }

        if(request.getIsSymbols()){
            sb.append(specialChars);
        }
        combination=sb.toString();
        
        if(request.getIsSimilarChar()){
            password= generatePasswordWithDup(combination, request.getPasswordLength());
        }else{
            password=generatePasswordNoDup(combination, request.getPasswordLength());
        }

        p.setPassword(password);
        
        try{
            passwordRepository.save(p);
        }
        catch(Exception e){
            createPassword(request);
        }

        return PasswordMapping.toResponse(p);

    }

    @Override
    public PasswordResponse editPassword(Integer passwordId, PasswordRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editPassword'");
    }

    @Override
    public PasswordResponse deletePassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePassword'");
    }

    @Override
    public PasswordResponse getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }




    
}
