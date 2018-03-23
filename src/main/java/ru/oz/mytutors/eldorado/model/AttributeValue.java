package ru.oz.mytutors.eldorado.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@ToString(exclude= {"facetValues", "facetValue"})
@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class AttributeValue {

    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private FacetValue facetValue;

    @ManyToOne(cascade = CascadeType.ALL)
    private Attribute attribute;

    private String value;
}
