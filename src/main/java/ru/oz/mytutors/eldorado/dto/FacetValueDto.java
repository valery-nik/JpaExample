package ru.oz.mytutors.eldorado.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FacetValueDto {

    private Long id;
    private String name;
    private String code;
    private Long facetId;
    private Boolean active;
    private Integer sortValue;

    @JsonProperty("attributeIdToValues")
    private Map<Long, Set<String>> attributeIdToValues;

    @Builder.Default
    private Set<Long> productIds = new HashSet<>(); // not empty only for manual facets.

}
