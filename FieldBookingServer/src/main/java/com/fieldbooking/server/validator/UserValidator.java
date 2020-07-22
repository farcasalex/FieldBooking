package com.fieldbooking.server.validator;

import com.fieldbooking.server.model.entity.User;
import com.fieldbooking.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

@Component
public class UserValidator {
    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUser(User user, BindingResult result) {

        if (!StringUtils.isEmpty(user.getUsername()) && user.getUsername() != null &&
                userRepository.findByUsernameIgnoreCase(user.getUsername()) != null) {
            result.rejectValue("username", "UsernameDuplicate", "username already taken");
        }

    }
}
