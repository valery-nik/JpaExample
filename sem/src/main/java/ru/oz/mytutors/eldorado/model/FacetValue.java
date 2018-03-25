package ru.oz.mytutors.eldorado.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@EqualsAndHashCode(exclude = {"rangedAttributeValue", "attributeValues", "tag", "facet"})
@ToString(exclude={"rangedAttributeValue", "rangedAttributeValue", "tag", "facet"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FacetValue {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "NAME")
    private String name;

    private String code;

    @JsonManagedReference
    @OneToMany(mappedBy = "facetValue", fetch = EAGER, cascade = CascadeType.ALL)
    private Set<AttributeValue> attributeValues = new HashSet<>(); // only simple AttributeValues

    @JsonManagedReference
    @OneToOne(mappedBy = "facetValue", cascade = CascadeType.ALL)
    private RangedAttributeValue rangedAttributeValue; // ranged AttributeValues

    @ManyToOne
    @JoinColumn(name="TAG_ID")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="FACET_ID")
    private Facet facet;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "VALUE_TO_FACET_VALUE",
            joinColumns = @JoinColumn(name = "FACET_VALUE_ID", referencedColumnName = "ID")
    )
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 100)
    @Column(name = "VALUE")
    private Set<String> values = new HashSet<>();
}