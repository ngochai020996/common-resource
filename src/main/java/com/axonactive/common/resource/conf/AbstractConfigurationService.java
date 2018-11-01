/**
 * 
 */
package com.axonactive.common.resource.conf;

import java.util.ResourceBundle;

import org.apache.commons.lang3.math.NumberUtils;

import com.axonactive.common.resource.AbstractResourceService;
import com.axonactive.common.resource.ResourceKey;

/**
 * @author nvmuon
 *
 */
public abstract class AbstractConfigurationService extends AbstractResourceService {
	
	private ResourceBundle.Control control;

	public AbstractConfigurationService(String resourceFileName,  ResourceBundle.Control control) {
		super(resourceFileName);
		this.control = control;
	}
    
     /**
     * @param key : key word of a property in resource file
     * @return a value string of key word in the language resource.
     */
    public abstract String get(String key);
    
    /**
     * @return a resource bundle with name match with bundle name + language locale
     */
    protected ResourceBundle loadBundle() {
        return ResourceBundle.getBundle(resourceFileName, control);
    }
    
    /**
     * @param key : key of a property in resource file. The key is an Enum that implements the {@link ResourceKey}
     * @return a value string of key word in the language resource.
     */
    @Override
    public String get(ResourceKey key) {
    	return get(key.getName());
    }
    
    /**
     * @param key : key of a property in resource file
     * @return a value as {@link Long} of key word in the language resource.
     */
    public Long getLong(String key) {
		String value = get(key);
		return NumberUtils.toLong(value);
	}
    
    /**
     * @param key : key of a property in resource file. The key is an Enum that implements the {@link ResourceKey}
     * @return a value as {@link Long} of key word in the language resource.
     */
    public Long getLong(ResourceKey key) {
    	return getLong(key.getName());
    }
    
    /**
     * @param key : key of a property in resource file
     * @return a value as {@link Integer} of key word in the language resource.
     */
    public Integer getInt(String key) {
		String value = get(key);
			return NumberUtils.toInt(value);
	}
    
    /**
     * @param key : key of a property in resource file. The key is an Enum that implements the {@link ResourceKey}
     * @return a value as {@link Integer} of key word in the language resource.
     */
    public Integer getInt(ResourceKey key) {
    	return getInt(key.getName());
    }
    
    /**
     * @param key : key of a property in resource file
     * @return a value as {@link Double} of key word in the language resource.
     */
    public Double getDouble(String key) {
		String value = get(key);
			return NumberUtils.toDouble(value);
	}
    
    /**
     * @param key : key of a property in resource file
     * @return a value as {@link Double} of key word in the language resource.
     */
    public Double getDouble(ResourceKey key) {
    	return getDouble(key.getName());
    }
}
