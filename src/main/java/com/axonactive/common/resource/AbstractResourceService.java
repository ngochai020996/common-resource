/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.resource;

/**
 *
 * @author nvmuon
 */
public abstract class AbstractResourceService {

	protected String resourceFileName;

	public AbstractResourceService(String resourceFileName) {
		this.resourceFileName = resourceFileName;
	}

	public abstract String get(String key);

	public String get(ResourceKey key) {
		return get(key.getName());
	}

}
