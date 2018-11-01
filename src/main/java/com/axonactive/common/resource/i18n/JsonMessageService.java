/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.resource.i18n;

import javax.json.JsonValue;

import com.axonactive.common.resource.JsonResourceBundleControl;
import com.axonactive.common.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author nvmuon
 */
public class JsonMessageService  extends AbstractMessageService  {

    public JsonMessageService(String resourceName) {
		super(resourceName, new JsonResourceBundleControl());
	}
    
	/**
	 * @param key : key word of a property in resource file from.
	 * @return a value string of key word in the language resource.
	 */
	public String get(String key) {
		JsonNode msgElement = (JsonNode)this.loadBundle().getObject(key);
		return msgElement.asText();
	}

	/**
	 * @param <T> : type of the returned object.
	 * @param clazz: the class of the returned object.
	 * @param key : key word of a property in resource file from.
	 * @return a value string of key word in the language resource.
	 */
	public <T> T get(String key, Class<T> clazz) {
		JsonValue msgElement = (JsonValue)this.loadBundle().getObject(key);
		return  JsonUtils.fromJson(msgElement.toString(), clazz);
	}
}
