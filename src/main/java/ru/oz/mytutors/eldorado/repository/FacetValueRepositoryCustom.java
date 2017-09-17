package ru.oz.mytutors.eldorado.repository;

import org.springframework.stereotype.Repository;
import ru.oz.mytutors.eldorado.model.AttributeValue;
import ru.oz.mytutors.eldorado.model.FacetValue;
import ru.oz.mytutors.eldorado.model.RangedAttributeValue;

import java.util.List;

@Repository
public interface FacetValueRepositoryCustom {

    FacetValue updateAttributeValues(FacetValue facetValue, List<AttributeValue> attributeValues, RangedAttributeValue rangedAttributeValue);
}
