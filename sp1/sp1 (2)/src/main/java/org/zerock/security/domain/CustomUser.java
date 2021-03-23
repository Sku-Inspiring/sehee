package org.zerock.security.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zerock.domain.MemberVO;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class CustomUser extends User {
    private static final long serialVersionUID = 1L;

    private MemberVO member;

    public CustomUser(String username, String password){
        super(username, password);
    }

    public CustomUser(MemberVO vo){
        super(vo.getUserid(), vo.getUserpw());

        this.member = vo;
    }
}
