package ru.oz.mytutors.eldorado.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.oz.mytutors.eldorado.model.Attribute;

public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {

}
