package com.spring.restAPI.controller;

import com.spring.restAPI.domain.Member;
import com.spring.restAPI.dto.ResponseDto;
import com.spring.restAPI.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Member>> getMember(@PathVariable String id) {
        ResponseDto<Member> result = memberService.getMember(id);
        if (result.getResultCode() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<Member>> memberRegister(@RequestBody Member member) {

        ResponseDto<Member> result = memberService.register(member);
        if (result.getResultCode() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<ResponseDto<Member>> modifyUser(@PathVariable String id, @RequestBody Member member){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<ResponseDto<Member>> disableUser(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
