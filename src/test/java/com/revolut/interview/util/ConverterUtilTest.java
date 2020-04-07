package com.revolut.interview.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.revolut.interview.util.ConverterUtil.calculateDaysToBirthday;
import static org.assertj.core.api.Assertions.assertThat;

class ConverterUtilTest {

    @Test
    void convertDate() {
        LocalDate dateOfBirthdayToday = LocalDate.now();

        final Long daysRemaining = calculateDaysToBirthday(dateOfBirthdayToday);

        assertThat(daysRemaining).isEqualTo(0);
    }

}