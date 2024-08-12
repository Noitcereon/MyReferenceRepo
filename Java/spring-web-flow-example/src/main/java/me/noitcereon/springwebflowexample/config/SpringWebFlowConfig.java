package me.noitcereon.springwebflowexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

/**
 * The Spring Web Flow configuration class, which is required to e.g. identify where web flow XML files are located.
 * @see <a href="https://docs.spring.io/spring-webflow/docs/3.0.0/reference/">Spring Web Flow Documentation</a>
 */
@Configuration
public class SpringWebFlowConfig extends AbstractFlowConfiguration {
    @Bean
    public FlowDefinitionRegistry flowRegistry() {
        return getFlowDefinitionRegistryBuilder()
                .setBasePath("/WEB-INF/flows")
                .addFlowLocationPattern("/**/*-flow.xml")
                .build();
    }
    @Bean
    public FlowExecutor flowExecutor() {
        return getFlowExecutorBuilder(flowRegistry()).build();
    }

    @Bean
    public FlowHandlerAdapter flowHandlerAdapter(){
        FlowHandlerAdapter flowAdapter = new FlowHandlerAdapter();
        flowAdapter.setFlowExecutor(flowExecutor());
        return flowAdapter;
    }
    @Bean
    public FlowHandlerMapping flowHandlerMapping(){
        FlowHandlerMapping flowMapper = new FlowHandlerMapping();
        flowMapper.setFlowRegistry(flowRegistry());
        flowMapper.setOrder(0);
        return flowMapper;
    }
}
