package com.fieldbooking.client.gateway.impl;

import com.fieldbooking.client.conf.RestProperties;
import com.fieldbooking.client.entity.Field;
import com.fieldbooking.client.gateway.AbstractGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FieldGatewayImpl implements AbstractGateway<Field> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldGatewayImpl.class);
    private final String URL = "/rest/field";

    private final RestProperties restProperties;

    public FieldGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public Field save(Field field) {
        LOGGER.info("Executing save method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(field);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, httpEntity, Field.class);
    }

    @Override
    public List<Field> findAll() {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        Field[] response = restTemplate.getForObject(url, Field[].class);
        return Arrays.asList(response);
    }

    @Override
    public Optional<Field> findById(Long id) {
        LOGGER.info("Executing findById method, id=" + id);
        String url = restProperties.getUrl() + URL + "/byId?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        Field field = restTemplate.getForObject(url, Field.class);
        return Optional.ofNullable(field);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Executing delete method, id=" + id);
        String url = restProperties.getUrl() + URL + "/delete";
        HttpEntity<Object> httpEntity = new HttpEntity<>(id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, httpEntity, void.class);
    }

    public List<Field> searchByAddress(String address) {
        LOGGER.info("Executing searchByAddress method, address=" + address);
        String url = restProperties.getUrl() + URL + "/byAddress?address=" + address;
        RestTemplate restTemplate = new RestTemplate();
        Field[] response = restTemplate.getForObject(url, Field[].class);
        return Arrays.asList(response);
    }

    public List<Field> findByFacilityId(Long id) {
        LOGGER.info("Executing findByFacilityId method, id=" + id);
        String url = restProperties.getUrl() + URL + "/byFacilityId?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        Field[] fields = restTemplate.getForObject(url, Field[].class);
        return Arrays.asList(fields);
    }
}
