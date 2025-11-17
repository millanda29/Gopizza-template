package com.gopizza.production.pizza.application.create;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePizzasOnOrderCreated {

    public void on(String json) throws JsonProcessingException {
    }

}
