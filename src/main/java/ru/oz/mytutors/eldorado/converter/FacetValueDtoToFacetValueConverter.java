package ru.oz.mytutors.eldorado.converter;

import org.springframework.core.convert.converter.Converter;
import ru.oz.mytutors.dto.FacetValueDto;
import ru.oz.mytutors.eldorado.model.AttributeValue;
import ru.oz.mytutors.eldorado.model.Facet;
import ru.oz.mytutors.eldorado.model.FacetValue;

import java.util.Set;
import java.util.stream.Collectors;

public class FacetValueDtoToFacetValueConverter implements Converter<FacetValueDto, FacetValue> {
    @Override
    public FacetValue convert(FacetValueDto facetValueDto) {

        Set<AttributeValue> attributeValues = facetValueDto.getAttributeValuesIds().stream()
                .map(id -> AttributeValue.builder().id(id).build())
                .collect(Collectors.toSet());

        return FacetValue.builder()
                .id(facetValueDto.getId())
                .attributeValues(attributeValues)
                .facet(Facet.builder().id(facetValueDto.getFacetId()).build())
                .build();
    }
}
