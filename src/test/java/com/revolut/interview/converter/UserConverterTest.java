package com.revolut.interview.converter;

import com.revolut.interview.accessor.User;
import com.revolut.interview.dto.Date;
import com.revolut.interview.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UserConverterTest {

    private UserConverter underTest = new UserConverter();

    @Test
    void convert() {

        final User actual = underTest.convert(Date.builder().dateOfBirth(LocalDate.of(1996,6,6)).build(), "test");

        assertThat(actual).isEqualToIgnoringGivenFields(aUserDto(), "id");
    }

    private UserDto aUserDto() {
        return UserDto.builder().userName("test").dateOfBirth(LocalDate.of(1996, 6, 6)).build();
    }

}