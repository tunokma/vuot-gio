package com.kma.quanlygiangday.security;

import com.kma.quanlygiangday.model.GiangVien;
import org.springframework.stereotype.Service;
import com.kma.quanlygiangday.model.User;
import com.kma.quanlygiangday.service.GiangVienService;
import com.kma.quanlygiangday.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


@Service
public class AuthUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserDetailsService.class);

    @Autowired
    private GiangVienService giangVienService;

    private org.springframework.security.core.userdetails.User springUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        GiangVien user = getUserDetail(username);
        if (user != null) {
            springUser = new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(user.getRole())
            );
            return springUser;
        } else {
            springUser = new org.springframework.security.core.userdetails.User("empty",
                    "empty",
                    false,
                    true,
                    true,
                    false,
                    getAuthorities(1l)
            );
            return springUser;
        }
    }

    public List<GrantedAuthority> getAuthorities(Long role) {

        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if (role == 1l) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (role == 2l) {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authList;
    }

    private GiangVien getUserDetail(String username) {

        GiangVien user = giangVienService.findByUsername(username);
        if (user == null) {
            logger.warn("user '" + username + "' on null!");
        } else {
            logger.info(user.toString());
        }
        return user;
    }
}
