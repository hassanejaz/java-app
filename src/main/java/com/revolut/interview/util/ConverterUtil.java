package com.revolut.interview.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ConverterUtil {

    public static Long calculateDaysToBirthday(LocalDate dateOfBirth) {
        LocalDate today = LocalDate.now();
        if (dateOfBirth.withYear(today.getYear()).compareTo(today) < 0) {
            return ChronoUnit.DAYS.between(today, dateOfBirth.withYear(today.getYear() + 1));
        }
        return ChronoUnit.DAYS.between(today, dateOfBirth.withYear(today.getYear()));
    }
}
