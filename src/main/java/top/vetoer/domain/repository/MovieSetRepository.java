package top.vetoer.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import top.vetoer.entity.Movie;
import top.vetoer.entity.MovieSet;

import javax.transaction.Transactional;
import java.util.List;

public interface MovieSetRepository extends JpaRepository<MovieSet,Long> {
    MovieSet findById(long id);

    MovieSet findByTitle(String title);

    List<MovieSet> findByTitleLike(String key);

    Page<MovieSet> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update MovieSet set description=?2 where id=?1")
    int updateDescById(Long id,String description);

}
