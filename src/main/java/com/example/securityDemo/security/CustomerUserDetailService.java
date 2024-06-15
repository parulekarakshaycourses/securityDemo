package com.example.securityDemo.security;

import com.example.securityDemo.entity.Employee;
import com.example.securityDemo.repo.EmpRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerUserDetailService implements UserDetailsService
{
    EmpRepo empRepo;
    String[] roles = {"ROLE_ADMIN", "ROLE_MARKETER", "ROLE_CUSTOMER"};

    public CustomerUserDetailService(EmpRepo empRepo) {
        this.empRepo = empRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Set<GrantedAuthority> authorities;
        Employee emp = empRepo.findByUsername(username);

        if(emp == null)
        {
            throw new UsernameNotFoundException("This user does not exists");
        }
        else
        {
            authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(roles[emp.getIdRole()]));
        }

        return new User(emp.getUsername(), emp.getPassword(), authorities);
    }
}
