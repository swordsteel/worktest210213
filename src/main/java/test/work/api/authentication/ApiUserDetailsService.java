package test.work.api.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.work.api.users.User;
import test.work.api.users.UserAuthority;
import test.work.api.users.UserRepository;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ApiUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username '" + username + "' not found");
        }
        return makeUserDetails(user.get());
    }

    private org.springframework.security.core.userdetails.User makeUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities().stream()
                        .map(UserAuthority::getAuthority)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet()));
    }

}
