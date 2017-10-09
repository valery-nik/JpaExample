package ru.oz.mytutors.eldorado.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.oz.mytutors.eldorado.model.AttributeValue;
import ru.oz.mytutors.eldorado.model.FacetValue;
import ru.oz.mytutors.eldorado.model.RangedAttributeValue;
import ru.oz.mytutors.eldorado.repository.FacetValueRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FacetValueRepositoryImpl implements FacetValueRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public FacetValue updateValues(FacetValue facetValue, List<Long> attributeValueIds, Long rangedAttributeValueId) {

        // delete old child objects
        RangedAttributeValue oldRanged = entityManager.find(RangedAttributeValue.class, facetValue.getRangedAttributeValue().getId());
        oldRanged.setFacetValue(null);
        entityManager.persist(oldRanged);


        facetValue.getAttributeValues().forEach((av) -> {
            AttributeValue oldAv = entityManager.find(AttributeValue.class, av.getId());
            oldAv.setFacetValue(null);
            entityManager.persist(oldAv);
        });

        // save new ranged
        RangedAttributeValue newRanged = entityManager.find(RangedAttributeValue.class, rangedAttributeValueId);
        newRanged.setFacetValue(facetValue);
        entityManager.persist(newRanged);

        // save new av
        attributeValueIds.forEach((id) -> {
            AttributeValue newAv = entityManager.find(AttributeValue.class, id);
            newAv.setFacetValue(facetValue);
            entityManager.persist(newAv);
        });

        // entityManager.flush(); not working
        return entityManager.find(FacetValue.class, facetValue.getId());
    }

}
