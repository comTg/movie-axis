package top.vetoer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.vetoer.comm.aop.LoggerManage;
import top.vetoer.domain.repository.MovieRepository;
import top.vetoer.entity.Movie;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController{

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(value = "/movies")
    @LoggerManage(description = "movie首页")
    public String show_movie(@RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "15") Integer size, Model model){
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Movie> pages = movieRepository.findAll(pageable);
        model.addAttribute("pages",pages);
        return "movie";
    }
}
