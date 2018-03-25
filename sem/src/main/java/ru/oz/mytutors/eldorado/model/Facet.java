package ru.oz.mytutors.eldorado.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Facet {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "facet", fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 100)
   // @Builder.Default
    private Set<FacetValue> facetValues;
}
