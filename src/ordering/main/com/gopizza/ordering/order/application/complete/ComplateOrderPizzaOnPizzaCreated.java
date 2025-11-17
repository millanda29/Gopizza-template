package com.gopizza.ordering.order.application.complete;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComplateOrderPizzaOnPizzaCreated {

    public void on(String json) throws JsonProcessingException {
    }

}
