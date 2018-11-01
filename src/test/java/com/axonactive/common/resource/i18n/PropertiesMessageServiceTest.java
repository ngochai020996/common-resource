/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.resource.i18n;

import static org.junit.Assert.assertThat;

import java.util.Locale;
import java.util.MissingResourceException;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.axonactive.common.util.LocaleThreadLocal;

/**
 *
 * @author nvmuon
 */
public class PropertiesMessageServiceTest {
	
	private static final String JSON_MESSAGE_BUNDLE = "com.axonactive.common.resource.i18n.message";

    
    private PropertiesMessageService messageResourceService;
    
    
    @Before
    public void init() {
        this.messageResourceService = new PropertiesMessageService(JSON_MESSAGE_BUNDLE);
    }
    
    @After
    public void deploy() {
        this.messageResourceService = null;
        LocaleThreadLocal.unset();
    }
    
    @Test
    public void shouldReturnMessageWhenGivenCodeIsValid() {
        String value = this.messageResourceService.get("SFER001");
        
        assertThat(value, Is.is("Source is not registered! Please register it before sending a packet."));
    }
    
    @Test
    public void shouldReturnMessageWhenGivenCodeAndParameterAreValid() {
        String value = this.messageResourceService.get("SFER002", "10101001");
        
        assertThat(value, Is.is("This source 10101001 had already sent a packet with the same creation time!"));
    }
    
    @Test
    public void shouldReturnVietnameseMessageWhenGivenCodeIsValid() {
    	LocaleThreadLocal.set(Locale.forLanguageTag("vi"));
        String value = this.messageResourceService.get("SFER001");
        assertThat(value, Is.is("Chưa đăng ký mã nguồn."));
    }
    
    @Test
    public void shouldReturnVietnameseMessageWhenGivenCodeAndParameterAreValid() {
    	LocaleThreadLocal.set(Locale.forLanguageTag("vi"));
        String value = this.messageResourceService.get("SFER002", "10101001");
        assertThat(value, Is.is("Mã Nguồn 10101001 đã tồn tại trong hệ thống"));
    }
    
    @Test(expected = MissingResourceException.class)
    public void shouldThrowExceptionWhenGivenCodeIsNotExsit() {
        this.messageResourceService.get("notexisting");
    }
}
