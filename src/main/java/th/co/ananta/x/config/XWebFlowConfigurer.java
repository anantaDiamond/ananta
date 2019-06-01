package th.co.ananta.x.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.config.FlowExecutorBuilder;
import org.springframework.webflow.conversation.impl.SessionBindingConversationManager;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import th.co.ananta.x.web.base.XEncodingInterceptor;
import th.co.ananta.x.web.base.XFlowHandlerAdapter;
import th.co.ananta.x.web.base.XLogInterceptor;
import th.co.ananta.x.web.base.XValidateInterceptor;

@Configuration
public class XWebFlowConfigurer extends AbstractFlowConfiguration {

	@Autowired
	private XWebMvcConfigurer xWebMvcConfigurer;

	@Bean
	public FlowExecutor flowExecutor() {
		FlowExecutorBuilder flowExecutorBuilder = getFlowExecutorBuilder(flowRegistry());
		SessionBindingConversationManager conversationManager = new SessionBindingConversationManager();
		conversationManager.setLockTimeoutSeconds(10 * 60);
		flowExecutorBuilder.setConversationManager(conversationManager);
		flowExecutorBuilder.setMaxFlowExecutions(1);
		flowExecutorBuilder.setMaxFlowExecutionSnapshots(0);
		return flowExecutorBuilder.build();
	}

	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		return getFlowDefinitionRegistryBuilder(flowBuilderServices()).setBasePath("/WEB-INF/flows").addFlowLocationPattern("/**/*-flow.xml").build();
	}

	@Bean
	public FlowHandlerMapping flowHandlerMapping() {
		FlowHandlerMapping fhm = new FlowHandlerMapping();
		fhm.setOrder(-1);
		fhm.setFlowRegistry(flowRegistry());
		fhm.setInterceptors(new XValidateInterceptor());
		fhm.setInterceptors(new XEncodingInterceptor());
		fhm.setInterceptors(new XLogInterceptor());
		return fhm;
	}

	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {
		FlowHandlerAdapter handlerAdapter = new XFlowHandlerAdapter();
		handlerAdapter.setFlowExecutor(flowExecutor());
		handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
		return handlerAdapter;
	}

	@Bean
	public FlowBuilderServices flowBuilderServices() {
		return getFlowBuilderServicesBuilder().setViewFactoryCreator(xWebMvcConfigurer.mvcViewFactoryCreator()).setValidator(validator()).setDevelopmentMode(true).build();
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

}