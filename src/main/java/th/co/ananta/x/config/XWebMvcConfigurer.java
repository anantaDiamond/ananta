package th.co.ananta.x.config;

import java.util.Collections;
import java.util.Locale;

import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceChainRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.view.FlowAjaxTiles3View;

import th.co.ananta.x.web.base.Constant;
import th.co.ananta.x.web.base.WildcardReloadableResourceBundleMessageSource;
import th.co.ananta.x.web.base.XEncodingInterceptor;
import th.co.ananta.x.web.base.XHttpSessionListener;
import th.co.ananta.x.web.base.XLogInterceptor;
import th.co.ananta.x.web.base.XTokenInterceptor;

@EnableWebMvc
@Configuration
public class XWebMvcConfigurer implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		ResourceHandlerRegistration rhr;
		ResourceChainRegistration rcr;
		rhr = registry.addResourceHandler("/static/**");
		rhr = rhr.addResourceLocations("/static/", "/webjars/");
		//rhr = rhr.setCacheControl(CacheControl.maxAge(1L, TimeUnit.DAYS).cachePublic());
		rcr = rhr.resourceChain(true);
		rcr = rcr.addResolver(new WebJarsResourceResolver());
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "/WEB-INF/pages/layout/**/layouts.xml", "/WEB-INF/pages/view/**/views.xml" });
		configurer.setCheckRefresh(true);
		return configurer;
	}

	@Bean
	public WildcardReloadableResourceBundleMessageSource messageSource() {
		WildcardReloadableResourceBundleMessageSource source = new WildcardReloadableResourceBundleMessageSource();
		source.setBasenames("classpath:/**/messages*");
		source.setDefaultEncoding("UTF-8");
		source.setCacheSeconds(3600);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean
	public HttpSessionListener httpSessionListener() {
		return new XHttpSessionListener();
	}

	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(FlowAjaxTiles3View.class);
		return resolver;
	}

	@Bean
	public MvcViewFactoryCreator mvcViewFactoryCreator() {
		MvcViewFactoryCreator mfc = new MvcViewFactoryCreator();
		mfc.setViewResolvers(Collections.singletonList(viewResolver()));
		mfc.setUseSpringBeanBinding(true);
		return mfc;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("th", "TH"));
		return localeResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new XTokenInterceptor());
		registry.addInterceptor(new XEncodingInterceptor());
		registry.addInterceptor(new XLogInterceptor());
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new DateFormatter(Constant.FORMAT_DATE));
	}

}