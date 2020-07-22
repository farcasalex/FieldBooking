package com.fieldbooking.server.service.implementation;

import com.fieldbooking.server.model.entity.User;
import com.fieldbooking.server.repository.UserRepository;
import com.fieldbooking.server.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements AbstractService<User> {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsernameEquals(username);
        if (user != null && user.getPassword().equals(password)) return user;
        else return null;
    }

}
