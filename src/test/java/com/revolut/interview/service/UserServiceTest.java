package com.revolut.interview.service;

import com.revolut.interview.accessor.User;
import com.revolut.interview.util.ConverterUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService underTest;

    @Mock
    private UserRepository userRepository;

    @Test
    void testWhenDateOfBirthIsToday() {
        User user = new User();
        user.setUserName("test");
        user.setDateOfBirth(LocalDate.now());
        given(userRepository.findByUserName("test")).willReturn(user);

        final ResponseEntity<String> actual = underTest.getDaysUntilBirthday("test");

        assertThat(actual.getBody()).isEqualTo("Hello test Happy Birthday");
    }

    @Test
    void testWhenDateOfBirthIsInThePast() {
        User user = new User();
        user.setDateOfBirth(LocalDate.of(1996, 6, 6));
        user.setUserName("test");
        given(userRepository.findByUserName("test")).willReturn(user);

        final ResponseEntity<String> actual = underTest.getDaysUntilBirthday("test");

        assertThat(actual.getBody()).isEqualTo("Hello test, your birthday is in: " +
                ConverterUtil.calculateDaysToBirthday(user.getDateOfBirth()) + " days");

    }

    @Test
    void testWhenUserCannotBeFound() {
        User user = new User();
        user.setDateOfBirth(LocalDate.of(1996, 6, 6));
        user.setUserName("test");
        given(userRepository.findByUserName("test")).willReturn(null);

        final ResponseEntity<String> actual = underTest.getDaysUntilBirthday("test");

        assertThat(actual.getBody()).isEqualTo("The user you are looking for cannot be found");

    }

    @Test
    void testWhenDateOfBirthIsInTheFuture() {
        User user = new User();
        user.setDateOfBirth(LocalDate.of(2996, 6, 6));
        user.setUserName("test");
        given(userRepository.save(user)).willThrow();

        final ResponseEntity<Object> actual = underTest.updateUser(user);

        assertThat(actual.getBody()).isEqualTo("The date of birth must be in the past");

    }

    @Test
    void shouldReturnHttpsStatusNoContent() {
        User user = new User();
        user.setDateOfBirth(LocalDate.of(1996, 6, 6));
        user.setUserName("test");
        given(userRepository.findByUserName("test")).willReturn(user);

        final ResponseEntity<Object> actual = underTest.updateUser(user);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}