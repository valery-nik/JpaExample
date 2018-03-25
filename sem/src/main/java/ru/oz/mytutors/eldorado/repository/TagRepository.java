package ru.oz.mytutors.eldorado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.oz.mytutors.eldorado.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
