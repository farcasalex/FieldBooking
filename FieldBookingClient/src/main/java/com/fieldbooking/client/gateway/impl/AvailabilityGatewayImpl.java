package com.fieldbooking.client.gateway.impl;

import com.fieldbooking.client.conf.RestProperties;
import com.fieldbooking.client.entity.Availability;
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
public class AvailabilityGatewayImpl implements AbstractGateway<Availability> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvailabilityGatewayImpl.class);
    private final String URL = "/rest/availability";

    private final RestProperties restProperties;

    @Autowired
    public AvailabilityGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public Availability save(Availability availability) {
        LOGGER.info("Executing save method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(availability);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, httpEntity, Availability.class);
    }

    @Override
    public List<Availability> findAll() {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        Availability[] response = restTemplate.getForObject(url, Availability[].class);
        return Arrays.asList(response);
    }

    @Override
    public Optional<Availability> findById(Long id) {
        LOGGER.info("Executing findById method, id=" + id);
        String url = restProperties.getUrl() + URL + "/byId?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        Availability availability = restTemplate.getForObject(url, Availability.class);
        return Optional.ofNullable(availability);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Executing delete method, id=" + id);
        String url = restProperties.getUrl() + URL + "/delete";
        HttpEntity<Object> httpEntity = new HttpEntity<>(id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, httpEntity, Void.class);
    }

    public List<Availability> findByFacilityId(Long id) {
        LOGGER.info("Executing findByFacilityId method, id=" + id);
        String url = restProperties.getUrl() + URL + "/byFacilityId?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        Availability[] availabilities = restTemplate.getForObject(url, Availability[].class);
        return Arrays.asList(availabilities);
    }
}
