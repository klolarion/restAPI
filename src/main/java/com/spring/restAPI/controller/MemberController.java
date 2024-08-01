package com.spring.restAPI.controller;

import com.spring.restAPI.domain.Member;
import com.spring.restAPI.dto.RegisterDto;
import com.spring.restAPI.dto.ResponseDto;
import com.spring.restAPI.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Member>> getMember(@PathVariable String id) {
        log.info("* Get member");
        ResponseDto<Member> result = memberService.getMember(id);
        log.debug(result.toString());
        if (result.getResultCode() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


    /*postman으로 테스트시 @RequestBody 없이해야 정상
    * Body -> bulk edit
    *
    * email:email@email
    * password:123456
    * name:tester1
    *
    * */
    @PostMapping("/")
    public ResponseEntity<ResponseDto<Member>> memberRegister(RegisterDto registerDto) {

        ResponseDto<Member> result = memberService.register(registerDto);
        if (result.getResultCode() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Member>> modifyUser(@RequestBody Member member){
        ResponseDto<Member> result = memberService.modifyUser(member);

        if(result.getResultCode()==0){
            ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Member>> disableUser(@PathVariable String id){
        ResponseDto<Member> result = memberService.disableUser(id);
        if(result.getResultCode()==0){
            ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
