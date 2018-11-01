/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.resource.conf;

import com.axonactive.common.resource.JsonResourceBundleControl;
import com.axonactive.common.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author nvmuon
 */
public class JsonConfigurationService extends AbstractConfigurationService {

    public JsonConfigurationService(String resourceName) {
        super(resourceName, new JsonResourceBundleControl());
    }
    
    /**
     * @param key : key word of a property in resource file from.
     * @return a value string of key word in the language resource.
     */
    @Override
    public String get(String key) {
        return ((JsonNode) loadBundle().getObject(key)).asText();
    }

    /**
     * @param <T> : type of the returned object.
     * @param clazz: the class of the returned object.
     * @param key : key word of a property in resource file from.
     * @return a value string of key word in the language resource.
     */
    public <T> T get(String key, Class<T> clazz) {
    	JsonNode val = (JsonNode) loadBundle().getObject(key);
        return JsonUtils.fromJsonNode(val, clazz);
    }
}
