/**
 * 
 */
package com.axonactive.common.resource.i18n;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import com.axonactive.common.resource.AbstractResourceService;
import com.axonactive.common.resource.ResourceKey;
import com.axonactive.common.util.LocaleThreadLocal;

import lombok.Getter;

/**
 * @author nvmuon
 *
 */
public abstract class AbstractMessageService extends AbstractResourceService {
		
	private static final String UNDER_SCORE = "_";

	private  ResourceBundle.Control control;
	
	@Getter
	protected Map<String, ResourceBundle> resourceBundlesMap;

    public AbstractMessageService(String resourceName, ResourceBundle.Control control) {
        super(resourceName);
        this.control = control;
        this.resourceBundlesMap = new HashMap<>();
    }    
   
    public  ResourceBundle loadBundle() {
    	Locale locale = LocaleThreadLocal.get();
        String bundleNameWithLocale = getBundleNameWithLocale(locale);
        
        return resourceBundlesMap
        		.computeIfAbsent(bundleNameWithLocale, k -> ResourceBundle.getBundle(resourceFileName, locale, control));
    }
    
    private String getBundleNameWithLocale(Locale locale) {
        String localeSuffix = (locale != null)? locale.toString() : "";
        return StringUtils.joinWith(UNDER_SCORE, this.resourceFileName, localeSuffix);
    }

    /**
     * @param key    key word of a message string property.
     * @param params to pass to the message string.
     * @return message string
     */
    public String get(String key, Object... params) {
        return MessageFormat.format(get(key), params);
    }
    
    /**
     * @param key  key of a message string property. The key is an Enum that implements the {@link ResourceKey}
     * @param params to pass to the message string.
     * @return message string
     */
    public String get(ResourceKey key, Object... params) {
        return MessageFormat.format(get(key).toString(), params);
    }

}
