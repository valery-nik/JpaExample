package ru.oz.mytutors.eldorado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.oz.mytutors.eldorado.model.FacetValue;

public interface FacetValueRepository extends JpaRepository<FacetValue, Long>, FacetValueRepositoryCustom {

}
