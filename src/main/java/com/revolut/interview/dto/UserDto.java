package com.revolut.interview.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Value
@Builder
@With
public class UserDto {

    @NotNull
    @Pattern(message = "The username must contain only letters", regexp = "^[a-zA-Z]+$")
    String userName;

    @NotEmpty
    LocalDate dateOfBirth;
}
