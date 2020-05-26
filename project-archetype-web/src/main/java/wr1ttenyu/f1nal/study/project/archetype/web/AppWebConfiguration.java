package wr1ttenyu.f1nal.study.project.archetype.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import io.undertow.UndertowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wr1ttenyu.f1nal.study.project.archetype.gracefulshutdown.GracefulShutdownUndertowWrapper;
import wr1ttenyu.f1nal.study.project.archetype.web.utils.LocalDateFormatter;
import wr1ttenyu.f1nal.study.project.archetype.web.utils.token.CurrentUserMethodArgumentResolver;
import wr1ttenyu.f1nal.study.project.archetype.web.utils.token.TokenHandleInterceptor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class AppWebConfiguration implements WebMvcConfigurer {

    @Autowired
    private GracefulShutdownUndertowWrapper gracefulShutdownUndertowWrapper;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenHandleInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentUserMethodArgumentResolver());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new LocalDateFormatter());
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter jsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        jsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        jsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(jsonHttpMessageConverter);
    }

    @Bean
    public UndertowServletWebServerFactory servletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addDeploymentInfoCustomizers(deploymentInfo -> deploymentInfo.addOuterHandlerChainWrapper(
                gracefulShutdownUndertowWrapper));
        factory.addBuilderCustomizers(builder -> builder.setServerOption(UndertowOptions.ENABLE_STATISTICS, true));
        return factory;
    }
}
