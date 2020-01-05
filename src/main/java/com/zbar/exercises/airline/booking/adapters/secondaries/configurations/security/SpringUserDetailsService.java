package com.zbar.exercises.airline.booking.adapters.secondaries.configurations.security;

import com.zbar.exercises.airline.booking.core.gateways.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpringUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.customerRepository.byUsername(username)
                .map(SpringUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not register"));
    }
}
