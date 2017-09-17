package ru.oz.mytutors.eldorado.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.oz.mytutors.eldorado.model.AttributeValue;
import ru.oz.mytutors.eldorado.model.FacetValue;
import ru.oz.mytutors.eldorado.model.RangedAttributeValue;
import ru.oz.mytutors.eldorado.repository.FacetValueRepository;
import ru.oz.mytutors.eldorado.repository.FacetValueRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class FacetValueRepositoryImpl implements FacetValueRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public FacetValue updateAttributeValues(FacetValue facetValue, List<AttributeValue> attributeValues, RangedAttributeValue rangedAttributeValue) {

        // delete old child objects
        RangedAttributeValue oldRanged = entityManager.find(RangedAttributeValue.class, facetValue.getRangedAttributeValue().getId());
        entityManager.remove(oldRanged);

        facetValue.getAttributeValues().forEach((av) -> {
            AttributeValue oldAv = entityManager.find(AttributeValue.class, av.getId());
            entityManager.remove(oldAv);
        });

        // save new
        rangedAttributeValue.setFacetValue(facetValue);
        entityManager.persist(rangedAttributeValue);

        attributeValues.forEach((av) -> {
            av.setFacetValue(facetValue);
            entityManager.persist(av);
        });

        //
//        facetValue.setRangedAttributeValue(rangedAttributeValue);

        return entityManager.find(FacetValue.class, facetValue.getId());
    }

}
