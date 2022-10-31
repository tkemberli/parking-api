package com.tk.parkingapi.service.authentication;

import com.tk.parkingapi.entity.SecurityUser;
import com.tk.parkingapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security service used to load users from the Database
 */
@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository
                .findByName(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
