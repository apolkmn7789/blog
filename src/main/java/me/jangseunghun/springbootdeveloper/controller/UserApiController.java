package me.jangseunghun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.jangseunghun.springbootdeveloper.dto.AddUserRequest;
import me.jangseunghun.springbootdeveloper.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request); // 회원가입 메서드 호출
        return "redirect:/login"; // 호원가입이 완료되면 로그인 페이지로 이동
    }
}
