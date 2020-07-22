package com.fieldbooking.client.gateway.impl;

import com.fieldbooking.client.conf.RestProperties;
import com.fieldbooking.client.entity.Facility;
import com.fieldbooking.client.entity.User;
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
public class FacilityGatewayImpl implements AbstractGateway<Facility> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacilityGatewayImpl.class);
    private final String URL = "/rest/facility";

    private final RestProperties restProperties;

    @Autowired
    public FacilityGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public Facility save(Facility facility) {
        LOGGER.info("Executing save method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(facility);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, httpEntity, Facility.class);
    }

    @Override
    public List<Facility> findAll() {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        Facility[] response = restTemplate.getForObject(url, Facility[].class);
        return Arrays.asList(response);
    }

    @Override
    public Optional<Facility> findById(Long id) {
        LOGGER.info("Executing findById method, id=" + id);
        String url = restProperties.getUrl() + URL + "/byId?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        Facility facility = restTemplate.getForObject(url, Facility.class);
        return Optional.ofNullable(facility);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Executing delete method, id=" + id);
        String url = restProperties.getUrl() + URL + "/delete";
        HttpEntity<Object> httpEntity = new HttpEntity<>(id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, httpEntity, void.class);
    }

    public List<Facility> searchByAddress(String address) {
        LOGGER.info("Executing searchByAddress method, address=" + address);
        String url = restProperties.getUrl() + URL + "/byAddress?address=" + address;
        RestTemplate restTemplate = new RestTemplate();
        Facility[] response = restTemplate.getForObject(url, Facility[].class);
        return Arrays.asList(response);
    }
}
