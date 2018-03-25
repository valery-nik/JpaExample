package ru.oz.mytutors.eldorado.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import ru.oz.mytutors.eldorado.converter.FacetValueDtoToFacetValueConverter;

@Configuration
public class MvcConfiguration {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new FacetValueDtoToFacetValueConverter());
        return service;
    }


}
