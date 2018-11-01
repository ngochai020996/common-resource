package com.axonactive.common.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axonactive.common.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonResourceBundleControl extends ResourceBundle.Control {

	public static final String CHARSET = "UTF-8";

	public static final String FORMAT_JSON = "json";

	private static final Logger log = LoggerFactory.getLogger(JsonResourceBundleControl.class);

	// Needed to cache all the json content as a string
	private static Map<String, String> bundlesContent = new HashMap<>();

	public String getJSONContent(String baseName, Locale locale) {
		if (baseName == null) {
			return null;
		}

		String jsonContentIndex = baseName;
		if (locale != null && locale.toString().compareToIgnoreCase("en") != 0) {
			jsonContentIndex = jsonContentIndex + locale.toString();
		}
		return bundlesContent.get(jsonContentIndex);
	}

	@Override
	public List<String> getFormats(String arg0) {
		return Arrays.asList(FORMAT_JSON);
	}

	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {
		if (!FORMAT_JSON.equals(format)) {
			log.debug("Format for bundle is not json ...");
			return null;
		}

		String bundleName = toBundleName(baseName, locale);
		String resourceName = toResourceName(bundleName, format);

		InputStream is = loader.getResourceAsStream(resourceName);
		if (is == null) {
			// Can happen, for example, if ResourceBundle searches a '_nl.json'
			// file while you only have a '_nl_NL.json' file
			log.debug("Get resource {} as stream not found ...", resourceName);
			return null;
		}

		// Read file as String
		StringBuilder sb;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is, CHARSET))) {
			sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}

		// Store the content in order to get it from the rest service
		String jsonContentIndex = baseName;
		if (locale != null) {
			jsonContentIndex = jsonContentIndex + locale.toString();
		}

		bundlesContent.put(jsonContentIndex, sb.toString());
		JsonNode rootNode = JsonUtils.toJsonNode(sb.toString());
		JSONResourceBundle rb = new JSONResourceBundle();
		
		rootNode.fields()
		.forEachRemaining(each -> rb.put(each.getKey(), each.getValue()));

		return rb;
	}

	public static class JSONResourceBundle extends ResourceBundle {
		private final Map<String, Object> data = new HashMap<>();

		@Override
		public Enumeration<String> getKeys() {
			return Collections.enumeration(data.keySet());
		}

		@Override
		protected Object handleGetObject(String key) {
			return data.get(key);
		}

		public void put(String key, Object value) {
			data.put(key, value);
		}
	}
}
