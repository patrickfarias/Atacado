package br.com.peixoto.atacadista.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MessageConfig {

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        final LocalValidatorFactoryBean localValidatorFactoryBean = new CustomValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        localValidatorFactoryBean.setParameterNameDiscoverer(new CustomParameterNameDiscoverer());
        return localValidatorFactoryBean;
    }

    public String getMessage(String code, Object... args) {
        return this.messageSource().getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
