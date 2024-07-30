package com.spring.restAPI.service;


import com.spring.restAPI.domain.Member;
import com.spring.restAPI.dto.RegisterDto;
import com.spring.restAPI.dto.ResponseDto;
import com.spring.restAPI.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final EntityManager em;

    public ResponseDto<Member> getMember(String id) {
        ResponseDto<Member> result = new ResponseDto<>();
        try {
            Optional<Member> memberOptional = memberRepository.findById(Long.valueOf(id));
            if (memberOptional.isPresent()) {
                result.setResultCode(0);
                result.setBody("Member found");
                result.setEntity(memberOptional.get());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultCode(1);
            result.setBody("Member not found");
            result.setEntity(null);
            return result;
        }
    }

    public ResponseDto<Member> register(RegisterDto registerDto) {
        ResponseDto<Member> result = new ResponseDto<>();
        try {
            Member savedMember = memberRepository.save(new Member(registerDto.getEmail(), registerDto.getPassword(), registerDto.getName()));
            result.setResultCode(0);
            result.setBody("User created");
            result.setEntity(savedMember);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultCode(1);
            result.setBody("Register failed");
            result.setEntity(null);
            return result;
        }
    }

    public ResponseDto<Member> modifyUser(String id){
        ResponseDto<Member> result = new ResponseDto<>();
        Member member = null;
        try {
            Optional<Member> memberOptional = memberRepository.findById(Long.valueOf(id));
            if (memberOptional.isPresent()) {
                member = memberOptional.get();
                member.disableUser();
            }
            result.setResultCode(0);
            result.setBody("Modify success");
            result.setEntity(null);

        }catch (Exception e){
            e.printStackTrace();
            result.setResultCode(1);
            result.setBody("Modify failed");
            result.setEntity(null);
            return result;
        }

        em.persist(member);
        em.flush();
        em.clear();
        return result;
    }

    public ResponseDto<Member> disableUser(String id){
        ResponseDto<Member> result = new ResponseDto<>();
        Member member = null;
        try {
            Optional<Member> memberOptional = memberRepository.findById(Long.valueOf(id));
            if (memberOptional.isPresent()) {
                member = memberOptional.get();
                member.disableUser();
            }
            result.setResultCode(0);
            result.setBody("Member disabled");
            result.setEntity(null);

        }catch (Exception e){
            e.printStackTrace();
            result.setResultCode(1);
            result.setBody("Disable failed");
            result.setEntity(null);
            return result;
        }

        em.persist(member);
        em.flush();
        em.clear();
        return result;
    }
}
