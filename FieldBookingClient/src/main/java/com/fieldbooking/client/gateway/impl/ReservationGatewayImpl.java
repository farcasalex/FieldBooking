package com.fieldbooking.client.gateway.impl;

import com.fieldbooking.client.conf.RestProperties;
import com.fieldbooking.client.entity.Reservation;
import com.fieldbooking.client.gateway.AbstractGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ReservationGatewayImpl implements AbstractGateway<Reservation> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationGatewayImpl.class);
    private final String URL = "/rest/reservation";

    private final RestProperties restProperties;

    @Autowired
    public ReservationGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public Reservation save(Reservation reservation) {
        LOGGER.info("Executing save method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(reservation);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, httpEntity, Reservation.class);
    }

    @Override
    public List<Reservation> findAll() {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        Reservation[] response = restTemplate.getForObject(url, Reservation[].class);
        return Arrays.asList(response);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        LOGGER.info("Executing findById method, id=" + id);
        String url = restProperties.getUrl() + URL + "/byId?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        Reservation reservation = restTemplate.getForObject(url, Reservation.class);
        return Optional.ofNullable(reservation);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Executing delete method, id=" + id);
        String url = restProperties.getUrl() + URL + "/delete";
        HttpEntity<Object> httpEntity = new HttpEntity<>(id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, httpEntity, void.class);
    }

    public List<Reservation> findByUserId(Long id) {
        LOGGER.info("Executing findByUserId method, id=" + id);
        String url = restProperties.getUrl() + URL + "/byUserId?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        Reservation[] response = restTemplate.getForObject(url, Reservation[].class);
        return Arrays.asList(response);
    }
}
