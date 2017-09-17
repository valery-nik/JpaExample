package ru.oz.mytutors.converter;

import org.springframework.core.convert.converter.Converter;
import ru.oz.mytutors.dto.FacetValueDto;
import ru.oz.mytutors.eldorado.model.FacetValue;

public class FacetValueDtoToFacetValueConverter implements Converter<FacetValueDto, FacetValue> {
    @Override
    public FacetValue convert(FacetValueDto facetValueDto) {
        return null;
    }
}
