package top.vetoer.service;

import top.vetoer.entity.MovieSet;

import java.util.List;

public interface MovieSetService {
    List<MovieSet> findByTitleLike(String key);
}
