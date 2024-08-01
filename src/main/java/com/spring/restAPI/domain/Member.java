package com.spring.restAPI.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "off_cd", nullable = false)
    private boolean offCD;

    public Member(String email, String password, String memberName) {
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.offCD = false;
    }


    public void changePassword(String password){
        this.password = password;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public void disableUser(){
        this.offCD = true;
    }


}
