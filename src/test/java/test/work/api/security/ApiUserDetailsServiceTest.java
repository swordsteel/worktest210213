package test.work.api.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import test.work.api.authentication.ApiUserDetailsService;
import test.work.api.users.UserAuthority;
import test.work.api.users.UserRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiUserDetailsServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    private ApiUserDetailsService service;

    @Test(expected = UsernameNotFoundException.class)
    public void UsernameNotFound() {
        // when
        service.loadUserByUsername("username");

        // then exception
    }

    @Test
    public void UsernameFound() {
        // given
        var username = "username";
        var password = "password";
        var authority = "role_user";

        when(repository.findByUsername(anyString())).thenReturn(Optional.of(test.work.api.users.User.builder()
                .username(username)
                .password(password)
                .authorities(Set.of(UserAuthority.builder()
                        .authority(authority)
                        .build()))
                .build()));

        // when
        var response = service.loadUserByUsername(username);

        // then
        verify(repository, times(1)).findByUsername(anyString());
        assertEquals(response.getUsername(), username);
        assertEquals(response.getPassword(), password);
        assertTrue(response.getAuthorities().stream().findFirst().orElseThrow().getAuthority().equals(authority));
    }

}