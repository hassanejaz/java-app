package com.revolut.interview.converter;

import com.revolut.interview.accessor.User;
import com.revolut.interview.dto.Date;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {

    public User convert(Date date, String userName) {
        User user = new User();
        user.setDateOfBirth(date.getDateOfBirth());
        user.setUserName(userName);
        return user;
    }
}
