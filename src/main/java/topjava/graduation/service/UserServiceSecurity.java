package topjava.graduation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topjava.graduation.model.User;
import topjava.graduation.repository.UserRepository;
import topjava.graduation.web.AuthUser;

import java.util.Optional;

@Service
public class UserServiceSecurity implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserServiceSecurity.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("Authenticating {}", email);
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
        return new AuthUser(optionalUser.orElseThrow(
                () -> new UsernameNotFoundException("User '" + email + "' was not found")));
    }
}