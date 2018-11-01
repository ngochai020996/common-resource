/**
 * 
 */
package com.axonactive.common.resource.conf;

import com.axonactive.common.resource.ResourceKey;

/**
 * @author nvmuon
 *
 */
public enum ConfigKey implements ResourceKey {
	CONF_SYSTEM_NAME("conf.system.name"),
	CONF_SYSTEM_PORT("conf.system.port"),
	CONF_SYSTEM_VERSION("conf.system.version"),
	CONF_EXCHANGE_RATE("conf.exchange.rate"),
	CONF_EXCHANGE_DEFAULT_VALUE("conf.exchange.defaultValue");
	
	private String name;

	/**
	 * @param name
	 */
	private ConfigKey(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	
}
