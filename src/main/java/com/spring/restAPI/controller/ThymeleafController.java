package com.spring.restAPI.controller;


import com.spring.restAPI.dto.RegisterDto;
import com.spring.restAPI.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ThymeleafController {
    private final MemberService memberService;

    //thymeleaf
    @GetMapping("/th/")
    public String hello(Model model) {
        log.info("* Thymeleaf get members");
        model.addAttribute("users", memberService.getAllMembers());
        return "hello";
    }

    @PostMapping("/th/add")
    public String addUser(@RequestParam String name) {
        RegisterDto registerDto = new RegisterDto(name, "123123", "email@email");
        memberService.register(registerDto);
        return "redirect:/";
    }
}
