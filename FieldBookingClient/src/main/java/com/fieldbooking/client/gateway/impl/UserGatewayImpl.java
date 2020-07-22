package com.fieldbooking.client.gateway.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fieldbooking.client.conf.RestProperties;
import com.fieldbooking.client.entity.User;
import com.fieldbooking.client.gateway.AbstractGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserGatewayImpl implements AbstractGateway<User>{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserGatewayImpl.class);
    private final String URL = "/rest/user";

    private final RestProperties restProperties;

    @Autowired
    public UserGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public User save(User user) {
        LOGGER.info("Executing save method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(user);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, httpEntity, User.class);
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        User[] response = restTemplate.getForObject(url, User[].class);
        return Arrays.asList(response);
    }

    @Override
    public Optional<User> findById(Long id) {
        LOGGER.info("Executing findById method, id=" + id);
        String url = restProperties.getUrl() + URL + "/byId?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(url, User.class);
        return Optional.ofNullable(user);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Executing delete method, id=" + id);
        String url = restProperties.getUrl() + URL + "/delete";
        HttpEntity<Object> httpEntity = new HttpEntity<>(id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, httpEntity, void.class);
    }

    public User login(String username, String password) {
        LOGGER.info("Executing login method, username=" + username + ", password=" + password);
        String url = restProperties.getUrl() + URL + "/login?username=" + username + "&&password=" + password;
        RestTemplate restTemplate = new RestTemplate();
        User response = restTemplate.getForObject(url, User.class);
        return response;
    }

}
