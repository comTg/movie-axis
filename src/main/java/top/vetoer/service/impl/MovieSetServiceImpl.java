package top.vetoer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.vetoer.domain.repository.MovieSetRepository;
import top.vetoer.entity.MovieSet;
import top.vetoer.service.MovieSetService;

import java.util.List;

@Service("movieSetService")
public class MovieSetServiceImpl implements MovieSetService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MovieSetRepository movieSetRepository;

    @Override
    public List<MovieSet> findByTitleLike(String key) {
        logger.debug("查询movieset的title 是: "+key);
        return movieSetRepository.findByTitleLike("%"+key+"%");
    }
}
