package top.vetoer.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import top.vetoer.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Movie findById(long id);

    List<Movie> findByCategoryId(long id);

    List<Movie> findByTitle(String title);

    List<Movie> findAllByOrderByCreateTimeDesc(Pageable pageable);

    Page<Movie> findAll(Pageable pageable);

}
