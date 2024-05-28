package com.example.backend.service;
import com.example.backend.DTO.UserDto;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.config.SecurityConfig;
@Service
public class UserServiceImpl implements UserServiceInter{

    private UserMapper userDAO = null;
    private SecurityConfig securityConfig = null;
    @Autowired
    public UserServiceImpl(UserMapper userDAO, SecurityConfig securityConfig) {
        this.userDAO = userDAO;
        this.securityConfig = securityConfig;
    }

    @Override
    public String registerUser(UserDto userDTO) {
        User existingUser = userDAO.findByEmail(userDTO.getEmail());
        if (existingUser!= null) {
            return "User with email " + userDTO.getEmail() + " already exists.";
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        // 密码加密
        user.setPassword(securityConfig.encodePassword(userDTO.getPassword()));
        userDAO.insertUser(user);
        return "User registered successfully.";
    }


}
