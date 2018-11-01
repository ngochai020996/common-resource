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

import com.axonactive.common.resource.conf.PropertiesConfigurationService;

/**
 *
 * @author nvmuon
 */
public class PropertiesMessageServiceTest {
	
	private static final String PROPERTIES_RESOURCE_BUNDLE = "com.axonactive.common.resource.conf.test-confuration";

    
    private PropertiesConfigurationService messageResourceService;
    
    
    @Before
    public void init() {
        this.messageResourceService = new PropertiesConfigurationService(PROPERTIES_RESOURCE_BUNDLE);
        
    }
    
    @After
    public void deploy() {
        this.messageResourceService = null;
    }
    
    @Test
    public void shouldReturnMessageWhenGivenLiteralCodeIsValid() {
        String value = this.messageResourceService.get("conf.system.name");
        assertThat(value, Is.is("test.axonactive.com"));
    }
    
    @Test
    public void shouldReturnMessageWhenGivenCodeIsValid() {
        String value = this.messageResourceService.get(ConfigKey.CONF_SYSTEM_NAME);
        
        assertThat(value, Is.is("test.axonactive.com"));
    }
    
    
    @Test
    public void shouldReturnIntegerWhenGivenLiteralCodeForIntValueIsValid() {
        Integer value = this.messageResourceService.getInt(ConfigKey.CONF_SYSTEM_PORT);
        
        assertThat(value, Is.is(8080));
    }
    
    @Test
    public void shouldReturnIntegerWhenGivenCodeForIntValueIsValid() {
        Integer value = this.messageResourceService.getInt("conf.system.port");
        
        assertThat(value, Is.is(8080));
    }
    
    @Test
    public void shouldReturnDoubleWhenGivenLiteralCodeForDoubleValueIsValid() {
        Double value = this.messageResourceService.getDouble("conf.exchange.rate");
        
        assertThat(value, Is.is(0.14));
    }
    
    @Test
    public void shouldReturnDoubleWhenGivenCodeForDoubleValueIsValid() {
        Double value = this.messageResourceService.getDouble(ConfigKey.CONF_EXCHANGE_RATE);
        
        assertThat(value, Is.is(0.14));
    }
    
    @Test
    public void shouldReturnLongWhenGivenLiteralCodeForLongValueIsValid() {
        Long value = this.messageResourceService.getLong("conf.exchange.defaultValue");
        
        assertThat(value, Is.is(332320329392L));
    }
    
    @Test
    public void shouldReturnLongWhenGivenCodeForLongValueIsValid() {
        Long value = this.messageResourceService.getLong(ConfigKey.CONF_EXCHANGE_DEFAULT_VALUE);
        
        assertThat(value, Is.is(332320329392L));
    }
    
    @Test
    public void shouldReturnEmptyWhenGivenCodeForLongValueIsValid() {
        Long value = this.messageResourceService.getLong(ConfigKey.CONF_EXCHANGE_DEFAULT_VALUE);
        
        assertThat(value, Is.is(332320329392L));
    }
    
    @Test(expected = MissingResourceException.class)
    public void shouldThrowExceptionWhenGivenCodeIsNotExsit() {
        this.messageResourceService.get("conf.notexisting");
    }
   
}
