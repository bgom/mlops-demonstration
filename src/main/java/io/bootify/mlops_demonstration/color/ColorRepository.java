package io.bootify.mlops_demonstration.color;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ColorRepository extends JpaRepository<Color, Long> {
    @Query(value = "SELECT MAX(c.batch) FROM Color c")
    Integer getMaxBatch();

    @Query(value = "SELECT c FROM Color c WHERE c.batch = ?1")
    List<Color> findByBatch(Integer batch);
}
