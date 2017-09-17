package ru.oz.mytutors.eldorado;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.oz.mytutors.eldorado.model.Attribute;
import ru.oz.mytutors.eldorado.model.AttributeValue;
import ru.oz.mytutors.eldorado.model.FacetValue;
import ru.oz.mytutors.eldorado.model.RangedAttributeValue;
import ru.oz.mytutors.eldorado.repository.*;

import java.util.Arrays;
import java.util.HashSet;

@Slf4j
@SpringBootApplication
public class EldoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EldoApplication.class, args);
    }

    @Autowired
    AttributeRepository attributeRepository;
    @Autowired
    FacetValueRepository facetValueRepository;
    @Autowired
    AttributeValueRepository attributeValueRepository;
    @Autowired
    RangedAttributeValueRepository rangedAttributeValueRepository;

    @Autowired
    ObjectMapper mapper;

    @Bean
    CommandLineRunner initDb() {
        return args -> {
            final Attribute диагональ = Attribute.builder().id(1L).name("Diagonal").build();
            attributeRepository.save(диагональ);

            final Attribute диагональ2 = Attribute.builder().id(2L).name("Diagonal2").build();
            attributeRepository.save(диагональ2);

            AttributeValue дюймов30 = AttributeValue.builder().id(1L).attribute(диагональ).build();
            AttributeValue дюймов40 = AttributeValue.builder().id(2L).attribute(диагональ).build();
            attributeValueRepository.save(дюймов40);

            FacetValue facetValue = FacetValue.builder()
                    .id(1L)
                    .attributeValues(new HashSet<>(Arrays.asList(дюймов30)))
                    .build();

            дюймов30.setFacetValue(facetValue);
            facetValueRepository.save(facetValue);

            RangedAttributeValue existRAV = rangedAttributeValueRepository.findByAttributeIdAndFacetValueId(
                    диагональ.getId(), facetValue.getId());
            if (existRAV == null) {
                RangedAttributeValue rangedAttributeValue = RangedAttributeValue.builder()
                        .facetValue(facetValue)
                        .minValue(10.0f)
                        .maxValue(20.0f)
                        .attribute(диагональ)
                        .build();

                rangedAttributeValueRepository.save(rangedAttributeValue);

                facetValue.setRangedAttributeValue(rangedAttributeValue);
            }

            RangedAttributeValue rav2 = RangedAttributeValue.builder()
                    .id(2L)
                    .minValue(30.f)
                    .maxValue(40.f)
                    .build();
            RangedAttributeValue rav3 = RangedAttributeValue.builder()
                    .id(3L)
                    .minValue(40.f)
                    .maxValue(50.f)
                    .build();
            rangedAttributeValueRepository.save(Arrays.asList(rav2, rav3));

            FacetValue fullFacetValue = facetValueRepository.findOne(1L);
            log.info(" FacetValue = {}", mapper.writeValueAsString(fullFacetValue));

            log.info("База проинициализирована!");
        };
    }

}
