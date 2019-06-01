package th.co.ananta.x.web.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.VfsResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class WildcardReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

	private static final Logger LOG = LoggerFactory.getLogger(WildcardReloadableResourceBundleMessageSource.class);

	private static final String PROPERTIESSUFFIX = ".properties";
	private static final String CLASSES = "/classes/";
	private static final String CLASSPATH = "classpath:";
	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

	@Override
	public void setBasenames(String... passedInBaseNames) {
		if (passedInBaseNames != null) {
			List<String> baseNames = new ArrayList<>();
			for (int i = 0, len = passedInBaseNames.length; i < len; i++) {
				String basename = StringUtils.trimToEmpty(passedInBaseNames[i]);
				if (StringUtils.isNotBlank(basename)) {
					try {
						Resource[] resources = resourcePatternResolver.getResources(basename);
						for (int j = 0, resLen = resources.length; j < resLen; j++) {
							Resource resource = resources[j];
							String baseName = getBaseName(resource);
							if (baseName != null) {
								baseNames.add(baseName);
							}
						}
					} catch (IOException e) {
						logger.error("No message source files found for basename " + basename + ".");
						logger.error(e);
					}
				}
				super.setBasenames(baseNames.toArray(new String[baseNames.size()]));
			}
		}
	}

	/**
	 * This method is used to get the basename from the resource URI.
	 *
	 * @param resource
	 * @return the baseName
	 * @throws IOException
	 */
	private String getBaseName(Resource resource) throws IOException {
		String baseName = null;
		String uri = resource.getURI().toString();
		if (!uri.endsWith(PROPERTIESSUFFIX)) {
			return baseName;
		}

		if (resource instanceof FileSystemResource || resource instanceof VfsResource) {
			baseName = CLASSPATH + StringUtils.substringBetween(uri, CLASSES, PROPERTIESSUFFIX);
		} else if (resource instanceof ClassPathResource) {
			baseName = StringUtils.substringBefore(uri, PROPERTIESSUFFIX);
		} else if (resource instanceof UrlResource) {
			baseName = CLASSPATH + StringUtils.substringBetween(uri, ".jar!/", PROPERTIESSUFFIX);
		} else {
			LOG.info("URI not matched any of the resource types..so just reading it as a string");
			baseName = CLASSPATH + StringUtils.substringBetween(uri, CLASSES, PROPERTIESSUFFIX);
		}
		return baseName;
	}
}
