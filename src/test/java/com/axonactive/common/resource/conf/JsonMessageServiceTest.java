/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.resource.conf;

import static org.junit.Assert.assertThat;

import java.util.MissingResourceException;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.axonactive.common.resource.conf.JsonConfigurationService;

/**
 *
 * @author nvmuon
 */
public class JsonMessageServiceTest {
    private static final String JSON_RESOURCE_BUNDLE = "com.axonactive.common.resource.conf.test-json-confuration";
   
    private JsonConfigurationService jsonConfigurationService;
    
    @Before
    public void init() {
        this.jsonConfigurationService = new JsonConfigurationService(JSON_RESOURCE_BUNDLE);
    }
    
    @After
    public void deploy() {
        this.jsonConfigurationService = null;
    }
    
    @Test
    public void shouldReturnMessageWhenGivenLiteralCodeIsValid() {
        String value = this.jsonConfigurationService.get("conf.system.name");
        assertThat(value, Is.is("awsome.axonactive.com"));
    }
    
    @Test
    public void shouldReturnMessageWhenGivenCodeIsValid() {
        String value = this.jsonConfigurationService.get(ConfigKey.CONF_SYSTEM_NAME);
        
        assertThat(value, Is.is("awsome.axonactive.com"));
    }
    
    
    @Test
    public void shouldReturnIntegerWhenGivenLiteralCodeForIntValueIsValid() {
        Integer value = this.jsonConfigurationService.getInt(ConfigKey.CONF_SYSTEM_PORT);
        
        assertThat(value, Is.is(8443));
    }
    
    @Test
    public void shouldReturnIntegerWhenGivenCodeForIntValueIsValid() {
        Integer value = this.jsonConfigurationService.getInt("conf.system.port");
        
        assertThat(value, Is.is(8443));
    }
    
    @Test
    public void shouldReturnDoubleWhenGivenLiteralCodeForDoubleValueIsValid() {
        Double value = this.jsonConfigurationService.getDouble("conf.exchange.rate");
        
        assertThat(value, Is.is(0.14));
    }
    
    @Test
    public void shouldReturnDoubleWhenGivenCodeForDoubleValueIsValid() {
        Double value = this.jsonConfigurationService.getDouble(ConfigKey.CONF_EXCHANGE_RATE);
        
        assertThat(value, Is.is(0.14));
    }
    
    @Test
    public void shouldReturnLongWhenGivenLiteralCodeForLongValueIsValid() {
        Long value = this.jsonConfigurationService.getLong("conf.exchange.defaultValue");
        
        assertThat(value, Is.is(332320329392L));
    }
    
    @Test
    public void shouldReturnLongWhenGivenCodeForLongValueIsValid() {
        Long value = this.jsonConfigurationService.getLong(ConfigKey.CONF_EXCHANGE_DEFAULT_VALUE);
        
        assertThat(value, Is.is(332320329392L));
    }
    
    @Test
    public void shouldReturnEmptyWhenGivenCodeForLongValueIsValid() {
        Long value = this.jsonConfigurationService.getLong(ConfigKey.CONF_EXCHANGE_DEFAULT_VALUE);
        
        assertThat(value, Is.is(332320329392L));
    }
    
    @Test(expected = MissingResourceException.class)
    public void shouldThrowExceptionWhenGivenCodeIsNotExsit() {
        this.jsonConfigurationService.get("conf.notexisting");
    }
    
   
}
