package ru.skypro.sitesforresaleofthings.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;
import ru.skypro.sitesforresaleofthings.repository.UserRepository;

/**
 * Реализация интерфейса UserDetails
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity userFromDb = userRepository.findByUsername(username);
        if (userFromDb == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserSecurity(userFromDb);
    }
}