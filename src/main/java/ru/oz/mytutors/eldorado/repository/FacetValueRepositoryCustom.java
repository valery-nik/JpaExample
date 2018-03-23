package ru.oz.mytutors.eldorado.repository;

import org.springframework.stereotype.Repository;
import ru.oz.mytutors.eldorado.model.FacetValue;

import java.util.List;

@Repository
public interface FacetValueRepositoryCustom {

    FacetValue updateValues(FacetValue facetValue, List<Long> attributeValueIds, Long rangedAttributeValueId);
}
