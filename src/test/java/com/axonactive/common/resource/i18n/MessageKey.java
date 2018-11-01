/**
 * 
 */
package com.axonactive.common.resource.i18n;

import com.axonactive.common.resource.ResourceKey;

/**
 * @author nvmuon
 *
 */
public enum MessageKey implements ResourceKey {
	;
	
	private String name;
	
	/**
	 * @param name
	 */
	private MessageKey(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
}
