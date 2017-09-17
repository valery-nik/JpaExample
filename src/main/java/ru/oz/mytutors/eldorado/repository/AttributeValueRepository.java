package ru.oz.mytutors.eldorado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.oz.mytutors.eldorado.model.AttributeValue;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
}
