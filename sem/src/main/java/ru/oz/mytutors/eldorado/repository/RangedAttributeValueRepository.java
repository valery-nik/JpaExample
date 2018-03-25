package ru.oz.mytutors.eldorado.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.oz.mytutors.eldorado.model.RangedAttributeValue;

import java.util.List;

public interface RangedAttributeValueRepository extends PagingAndSortingRepository<RangedAttributeValue, Long> {

    RangedAttributeValue findByAttributeIdAndFacetValueId(long attributeId, long facetValueId);

//    @Query(value = "SELECT distinct f FROM Facet f " +
//            "LEFT JOIN FETCH f.facetValues fv " +
//            "LEFT JOIN FETCH fv.attribute fva " +
//            "LEFT JOIN FETCH fv.tags " +
//            "LEFT JOIN FETCH fv.values " +
//            "WHERE f.categoryId = ?1 AND f.active = true ")
//    List<Facet> findActiveByCategory(long categoryId);

}
