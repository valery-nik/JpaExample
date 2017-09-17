package ru.oz.mytutors.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacetValueDto {

    private Long id;
    private Set<Long> attributeValuesIds = new HashSet<>(); // only simple AttributeValues
    private Long rangedAttributeValueId; // ranged AttributeValues

}
