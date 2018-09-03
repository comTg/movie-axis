package top.vetoer.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.vetoer.entity.MovieSet;

import java.util.List;

public interface MovieSetRepository extends JpaRepository<MovieSet,Long> {
    MovieSet findByTitle(String title);

    List<MovieSet> findByTitleLike(String key);
}
