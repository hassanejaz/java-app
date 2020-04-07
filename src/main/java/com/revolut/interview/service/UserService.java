package com.revolut.interview.service;

import com.revolut.interview.accessor.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.revolut.interview.util.ConverterUtil.calculateDaysToBirthday;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<Object> updateUser(User newUser) {
        try {
            final User existentUser = userRepository.findByUserName(newUser.getUserName());
            if (null == existentUser) {
                userRepository.save(newUser);
            } else {
                existentUser.setDateOfBirth(newUser.getDateOfBirth());
                userRepository.save(existentUser);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("The date of birth must be in the past", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> getDaysUntilBirthday(String username) {

        try {
            final User user = userRepository.findByUserName(username);
            if (calculateDaysToBirthday(user.getDateOfBirth()) == 0) {
                return new ResponseEntity<>("Hello " + username + " Happy Birthday", HttpStatus.OK);
            }

            return new ResponseEntity<>("Hello " + username + ", your birthday is in: " +
                    calculateDaysToBirthday(user.getDateOfBirth()) + " days", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("The user you are looking for cannot be found", HttpStatus.NOT_FOUND);
        }

    }
}
