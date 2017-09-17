package ru.oz.mytutors.eldorado.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@EqualsAndHashCode(exclude = {"rangedAttributeValue", "attributeValues"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FacetValue {

    @Id
    @GeneratedValue
    Long id;

    @JsonManagedReference
    @OneToMany(mappedBy = "facetValue", fetch = EAGER, cascade = CascadeType.ALL)
    private Set<AttributeValue> attributeValues = new HashSet<>(); // only simple AttributeValues

    @JsonManagedReference
    @OneToOne(mappedBy = "facetValue", cascade = CascadeType.ALL)
    private RangedAttributeValue rangedAttributeValue; // ranged AttributeValues
}