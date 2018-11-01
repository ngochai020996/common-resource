/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.resource.conf;

import com.axonactive.common.resource.PropertiesResourceBundleControl;

/**
 *
 * @author nvmuon
 */
public class PropertiesConfigurationService extends AbstractConfigurationService {
    
    public PropertiesConfigurationService(String resourceFileName) {
        super(resourceFileName, new PropertiesResourceBundleControl());
    }
    
    /**
     * @param key : key word of a property in resource file
     * @return a value string of key word in the language resource.
     */
    public String get(String key) {
        String val = loadBundle().getString(key);
        return val;
    }
}
