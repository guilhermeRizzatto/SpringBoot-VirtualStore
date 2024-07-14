package com.guilhermerizzatto.virtualstore.authorization;

import com.guilhermerizzatto.virtualstore.dao.implementation.CustomerDaoImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationCustomerService implements UserDetailsService {

    private CustomerDaoImpl customerImpl = new CustomerDaoImpl();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerImpl.findByUsername(username);
    }
}
