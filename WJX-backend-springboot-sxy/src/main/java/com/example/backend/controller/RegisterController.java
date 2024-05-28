package com.example.backend.controller;
import com.example.backend.DTO.UserDto;
import com.example.backend.config.SecurityConfig;
import com.example.backend.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// 定义了UserController类，用于处理用户相关的请求。
// 该类使用@RestController注解，表示它是一个控制器类

@RequestMapping("/api/users")
@RestController
public class RegisterController extends SecurityConfig {

    private final UserServiceImpl userService;

    @Autowired
    public RegisterController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // 定义了registerUser方法，用于处理用户注册请求。
    // 该方法使用@PostMapping注解，表示它是一个HTTP POST请求处理方法。
    // 该方法接收一个UserDTO对象作为参数，并调用UserService的registerUser方法来处理用户注册请求。
    // 该方法返回一个 ResponseEntity 对象，其中包含注册成功或失败的消息。
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDTO) {
        String response = userService.registerUser(userDTO);
        if ("User registered successfully.".equals(response)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
