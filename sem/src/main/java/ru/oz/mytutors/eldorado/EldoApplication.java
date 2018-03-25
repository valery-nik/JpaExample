package ru.oz.mytutors.eldorado;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.oz.mytutors.eldorado.configs.PersistenceContext;
import ru.oz.mytutors.eldorado.model.*;
import ru.oz.mytutors.eldorado.repository.*;

import java.util.List;

@Slf4j
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class}
)
@Import(PersistenceContext.class)
@SpringBootApplication
public class EldoApplication {

    @Autowired
    TagRepository tagRepository;

   // @Autowired
   // TagService tagService;

    @Autowired
    PlatformTransactionManager transactionManager;

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
    CommandLineRunner test() {
        return args -> {
            Tag tag = tagRepository.findOne(1L);

            TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
             //  List<FacetValue> facetValues =  tagService.getFacetValuesByTagId(1L); // tag.getFacetValues();
             //   facetValues.forEach(System.out :: println);

                transactionManager.commit(txStatus);
            } catch (Exception ex) {
                transactionManager.rollback(txStatus);
                throw new RuntimeException(ex);
            }

        };
    }

//    @Bean
//    CommandLineRunner initDb() {
//        return args -> {
//            final Attribute диагональ = Attribute.builder().id(1L).name("Diagonal").build();
//            attributeRepository.save(диагональ);
//
//            final Attribute диагональ2 = Attribute.builder().id(2L).name("Diagonal2").build();
//            attributeRepository.save(диагональ2);
//
//            AttributeValue дюймов30 = AttributeValue.builder().id(1L).attribute(диагональ).build();
//            AttributeValue дюймов40 = AttributeValue.builder().id(2L).attribute(диагональ).build();
//            attributeValueRepository.save(дюймов40);
//
//            FacetValue facetValue = FacetValue.builder()
//                    .id(1L)
//                    .attributeValues(new HashSet<>(Arrays.asList(дюймов30)))
//                    .build();
//
//            дюймов30.setFacetValue(facetValue);
//            facetValueRepository.save(facetValue);
//
//            RangedAttributeValue existRAV = rangedAttributeValueRepository.findByAttributeIdAndFacetValueId(
//                    диагональ.getId(), facetValue.getId());
//            if (existRAV == null) {
//                RangedAttributeValue rangedAttributeValue = RangedAttributeValue.builder()
//                        .facetValue(facetValue)
//                        .minValue(10.0f)
//                        .maxValue(20.0f)
//                        .attribute(диагональ)
//                          .build();
//
//                rangedAttributeValueRepository.save(rangedAttributeValue);
//
//                facetValue.setRangedAttributeValue(rangedAttributeValue);
//            }
//
//            RangedAttributeValue rav2 = RangedAttributeValue.builder()
//                    .id(2L)
//                    .minValue(30.f)
//                    .maxValue(40.f)
//                    .build();
//            RangedAttributeValue rav3 = RangedAttributeValue.builder()
//                    .id(3L)
//                    .minValue(40.f)
//                    .maxValue(50.f)
//                    .build();
//            rangedAttributeValueRepository.save(Arrays.asList(rav2, rav3));
//
//            FacetValue fullFacetValue = facetValueRepository.findOne(1L);
//            log.info(" FacetValue = {}", mapper.writeValueAsString(fullFacetValue));
//
//            log.info("База проинициализирована!");
//        };
//    }

}
