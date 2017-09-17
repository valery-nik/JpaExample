package ru.oz.mytutors.eldorado.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "RANGED_ATTRIBUTE_VALUE",
//       uniqueConstraints =
//        @UniqueConstraint(columnNames={"facet_value_id", "attribute_id"}))
@Builder
public class RangedAttributeValue {

    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    private FacetValue facetValue;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Attribute attribute;

    @JsonProperty("min")
    private Float minValue;

    @JsonProperty("max")
    private Float maxValue;

}