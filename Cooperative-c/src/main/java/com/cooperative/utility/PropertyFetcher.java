package com.cooperative.utility;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertyFetcher {
	private static Log LOGGER = LogFactory.getLog(PropertyFetcher.class);

	private static HashMap<Object, Object> propertiesMap = new HashMap<Object, Object>();

	private static Properties commonProperties = null;
	public static final Timer refreshTimer = new Timer();

	public static final String MISSING_BUNDLE_EXCEPTION = "MISSING_BUNDLE_EXCEPTION";
	public static final String MISSING_PROPERTY_EXCEPTION = "MISSING_PROPERTY_EXCEPTION";

	/**
	 * Protected Constructor added as all members of this class are static and
	 * it will be visible to derived classes as well
	 */
	protected PropertyFetcher() {
		super();
	}

	/**
	 * This methd is used to display all the properties for that particular
	 * component
	 */
	public static void displayProperties() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("PROPERTIES START ===========================");
			LOGGER.debug("Total Number of contents in the List is "
					+ new ArrayList<Object>(commonProperties.keySet()).size());
		}
		final Iterator<Object> iterator = commonProperties.keySet().iterator();
		while (iterator.hasNext()) {
			final String propertyValue = (String) iterator.next();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("-----------------------------------");
				LOGGER.debug("PROPERTY VALUE= " + propertyValue);
				LOGGER.debug("-----------------------------------");
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("PROPERTIES END =========================== ");
		}
	}

	public String getPropertyValue(String key) {
		if (commonProperties != null) {
			return commonProperties.getProperty(key);
		} else {
			throw new RuntimeException(MISSING_PROPERTY_EXCEPTION
					+ " for the Key =" + key);
		}
	}

	public static ArrayList<String> getListFromStringArray(String commaString) {
		final ArrayList<String> returnList = new ArrayList<String>();
		final String[] array = commaString.split(",");
		for (int i = 0; i < array.length; i++) {
			if (!"".equals(array[i].trim())) {
				returnList.add(array[i].trim());
			}
		}
		return returnList;
	}

	/**
	 * Gets the property from the Property Cache
	 * 
	 * @param compName
	 * @param key
	 * @return String property value
	 */
	public static String getPropertyValue(String compName, String key) {
		Locale locale = new Locale("");
		compName+=locale.getLanguage();
		return getValueForKey(compName, key, locale);
	}

	public static String getPropertyValue(String compName, String key, Locale locale) {
		compName+="_"+locale.getLanguage();
		return getValueForKey(compName, key, locale);
	}
	
	/**
	 * Fetches the value from Resource Bundle using the key passed.
	 * 
	 * @param key
	 *            key used to fetch the value.
	 * @return String value fetched from the property file.
	 * @throws RuntimeException
	 *             Thrown by the method to indicate error while fetching the
	 *             value.
	 */
	private static String getValueForKey(String compName, String key, Locale locale) {
		Properties properties = (Properties) propertiesMap.get(compName);
		// Safety Check
		if (properties == null){ // Try to initialise the properties for the
			// Component
			initializePropertiesForComponent(compName, locale);
			properties = (Properties) propertiesMap.get(compName);
		}
		String fetchedValue = properties.getProperty(key);
		if (fetchedValue != null) {
			fetchedValue = fetchedValue.trim();
		} else {
			throw new RuntimeException(MISSING_PROPERTY_EXCEPTION
					+ " for KEY '" + key + "'");
		}
		return fetchedValue;
	}

	/**
	 * Intializes and loads the static and dynamic properties for the specified
	 * component.
	 * 
	 * @param compName
	 */
	private synchronized static void initializePropertiesForComponent(
			String compName, Locale locale) {
		try {
			// Check if the Component is already deployed
			final Properties properties = (Properties) propertiesMap
			.get(compName);
			if (properties == null) {
				final Properties totalProperties = getStaticProperties(compName, locale);

				propertiesMap.put(compName, totalProperties);
			}
		} catch (final Exception e) {
			throw new RuntimeException(
					"Failed to initialise properties for Component '"
					+ compName + "'", e);
		}
	}

	/**
	 * This method is used to obtain the static properties(from properties file)
	 * for the specified component
	 * 
	 * @param compName
	 * @return Properties
	 */
	private static Properties getStaticProperties(String compName, Locale locale) {
		Properties staticProperty = null;
		try {
			staticProperty = new Properties();
			final ResourceBundle resourceBundle = ResourceBundle
			.getBundle(compName, locale);
			final Enumeration<String> enumeration = resourceBundle.getKeys();
			while (enumeration.hasMoreElements()) {
				final String property = (String) enumeration.nextElement();
				staticProperty.setProperty(property, resourceBundle
						.getString(property));
			}
		} catch (final MissingResourceException mre) {
			throw new RuntimeException(MISSING_BUNDLE_EXCEPTION + " = "
					+ compName + ".properties", mre);
		}
		return staticProperty;
	}
}
