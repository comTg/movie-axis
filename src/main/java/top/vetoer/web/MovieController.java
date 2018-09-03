package top.vetoer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.vetoer.comm.aop.LoggerManage;
import top.vetoer.domain.repository.MovieRepository;
import top.vetoer.domain.repository.MovieSetRepository;
import top.vetoer.domain.result.ExceptionMsg;
import top.vetoer.domain.result.Response;
import top.vetoer.domain.result.ResponseData;
import top.vetoer.entity.Movie;
import top.vetoer.entity.MovieSet;
import top.vetoer.service.MovieSetService;
import top.vetoer.utils.DateUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieController extends BaseController {
    @Autowired
    private MovieSetRepository movieSetRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Resource
    private MovieSetService movieSetService;

    @PostMapping(value = "/add")
    @LoggerManage(description = "增加movie")
    public Response addMovie(Movie movie) {
        logger.info(movie.toString());
        try {
            if(null==movie.getTitle() || "".equals(movie.getTitle().trim())){
                return result(ExceptionMsg.TitleEmpty);
            }
            MovieSet movieSet = movieSetRepository.findByTitle(movie.getTitle());
            if (null == movieSet) {
                movieSet = new MovieSet();
                movieSet.setTitle(movie.getTitle());
                movieSet.setType(movie.getType());
                movieSet.setCreateTime(DateUtils.getCurrentTime());
                movieSet.setLastModifyTime(DateUtils.getCurrentTime());
                movieSetRepository.save(movieSet);
                logger.info("movie set 不存在,重新生成为: " + movieSet.toString());
            } else {
                movieSet.setLastModifyTime(DateUtils.getCurrentTime());
            }
            String episode = movie.getEpisode();
            if (episode.indexOf("-") > 0) {
                String start = episode.substring(0, episode.indexOf("-"));
                String end = episode.substring(episode.indexOf("-") + 1);
                logger.info("集数范围为:"+start+","+end);
                for (int i = Integer.valueOf(start); i <= Integer.valueOf(end); i++) {
                    Movie temp = movie.clone();
                    temp.setEpisode(i + "");
                    temp.setCreateTime(DateUtils.getCurrentTime());
                    temp.setCategoryId(movieSet.getId());
                    movieRepository.save(temp);
                }
            } else {
                movie.setCreateTime(DateUtils.getCurrentTime());
                movie.setCategoryId(movieSet.getId());
                movieRepository.save(movie);
            }
            return result();
        } catch (Exception e) {
            logger.error("add failed", e);
            return result(ExceptionMsg.FAILED);
        }
    }

    @PostMapping(value = "/steamload")
    @LoggerManage(description = "首页流加载数据")
    public ResponseData steamLoad(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "15") Integer size){
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Movie> pages = movieRepository.findAll(pageable);
        return new ResponseData(pages);
    }


    @PostMapping(value = "/queryset")
    @LoggerManage(description = "查询movieset")
    public ResponseData queryMovieSet(String key) {
        if(key!=null && !"".equals(key.trim())){
            List<MovieSet> movieSets = movieSetService.findByTitleLike(key);
            if(movieSets!=null && movieSets.size()>0){
                String[] res = new String[movieSets.size()];
                for(int i = 0;i<movieSets.size();i++){
                    res[i] = movieSets.get(i).getTitle();
                }
                return new ResponseData(res);
            }
        }
        String[] res = {"无历史数据"};
        return new ResponseData(res);
    }

}
