//package com.xzx;
//
//import com.xzx.entity.Movie;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class MovieDemoApplicationTests {
//
//    @Autowired
//    private MovieMapper movieMapper;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void test01() {
//        MovieExample example = new MovieExample();
//        MovieExample.Criteria criteria = example.createCriteria();
//        criteria.andTitleLike("%战%");
//        List<Movie> movie1s = movieMapper.selectByExample(example);
//
//        System.out.println("size" + movie1s.size());
//        for (Movie movie:movie1s) {
//            System.out.println(movie.getTitle());
//        }
//    }
//
//}
