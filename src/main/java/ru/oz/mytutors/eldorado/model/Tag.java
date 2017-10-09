package ru.oz.mytutors.eldorado.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString(exclude="facetValues")
@EqualsAndHashCode(exclude = {"facetValues"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue()
    private Long id;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;

//    @Column(name = "PRODUCT_IDS")
//    @Convert(converter = IdsConverter.class)
//    private List<Long> products;

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private List<FacetValue> facetValues;

}
