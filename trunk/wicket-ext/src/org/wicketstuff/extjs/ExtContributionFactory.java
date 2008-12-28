package org.wicketstuff.extjs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.wicket.util.lang.Objects;
import org.apache.wicket.util.string.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default Bundle contribution factory. It will load the bundle contribution specified by a <code>ext.properties</code>
 * file found on the application classpath
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtContributionFactory {

	private static final Logger log = LoggerFactory.getLogger(ExtContributionFactory.class);

	/**
	 * Defauld load strategy
	 */
	public ExtContribution load() {
		Enumeration<URL> resources;
		try {
			resources = Thread.currentThread()
					.getContextClassLoader()
					.getResources("META-INF/ext.properties");

			while (resources.hasMoreElements()) {
				InputStream in = null;
				try
				{
					final URL url = resources.nextElement();
					final Properties properties = new Properties();
					in = url.openStream();
					properties.load(in);
					String bundle = properties.getProperty("bundle");
					if( !Strings.isEmpty(bundle) ) {
						log.info("Loading Wicket-Ext bundle class --> {}", bundle);
						return (ExtContribution) Objects.newInstance(bundle);
					}

				}
				finally
				{
					if (in != null)
					{
						in.close();
					}
				}
			}

		} catch (IOException e) {
			log.error("Unable to load 'ext.properties'", e);
		}

		throw new RuntimeException("Unable to initialize Wicket-Ext. You must provide the Ext resources bundle factory in the 'ext.properties' configuration file ");

	}

}
