package com.axonactive.common.resource.i18n;

import com.axonactive.common.resource.PropertiesResourceBundleControl;

public class PropertiesMessageService extends AbstractMessageService {
	
    public PropertiesMessageService(String resourceName) {
		super(resourceName, new PropertiesResourceBundleControl());
	}

    /**
     * @param key : key word of a property in resource file from.
     * @return a value string of key word in the language resource.
     */
    public String get(String key) {
        return this.loadBundle().getString(key);
    }
}
