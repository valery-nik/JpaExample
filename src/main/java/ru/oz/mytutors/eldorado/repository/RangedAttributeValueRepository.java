package ru.oz.mytutors.eldorado.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.oz.mytutors.eldorado.model.RangedAttributeValue;

public interface RangedAttributeValueRepository extends PagingAndSortingRepository<RangedAttributeValue, Long> {

    RangedAttributeValue findByAttributeIdAndFacetValueId(long attributeId, long facetValueId);
}
