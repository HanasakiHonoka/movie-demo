package com.xzx;

import com.xzx.dto.PeopleWithBox;
import com.xzx.mapper.ActorMapper;
import com.xzx.servie.IActorParticipateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Classname ActorTest
 * @Description
 * @Date 2020/10/1 13:27
 * @Author XZX
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActorTest {

    @Autowired
    ActorMapper actorMapper;

    @Autowired
    IActorParticipateService actorParticipateService;

    @Test
    public void topActorTest() {
        List<PeopleWithBox> topActorWithBox = actorMapper.getTopActorWithBox();
        System.out.println(topActorWithBox);
    }

    @Test
    public void testap() {
        System.out.println(actorParticipateService.getByMovieId(10).size());
    }
}
