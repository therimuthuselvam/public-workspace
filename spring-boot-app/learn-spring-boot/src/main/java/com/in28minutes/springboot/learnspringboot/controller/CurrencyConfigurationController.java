package com.in28minutes.springboot.learnspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.learnspringboot.configuration.CurrencyServiceConfiguration;

@RestController
public class CurrencyConfigurationController {

  @Autowired
  private CurrencyServiceConfiguration currencyConfiguration;

  @RequestMapping("currency-configuration")
  public CurrencyServiceConfiguration retrieveAllCurrencyConfiguration() {
    return currencyConfiguration;
  }
}
