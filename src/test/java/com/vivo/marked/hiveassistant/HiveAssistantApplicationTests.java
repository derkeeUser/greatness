package com.vivo.marked.hiveassistant;

import com.vivo.marked.hiveassistant.dao.StatementDao;
import com.vivo.marked.hiveassistant.dao.UserDao;
import com.vivo.marked.hiveassistant.entity.StatementInfo;
import com.vivo.marked.hiveassistant.entity.UserInfo;
import com.vivo.marked.hiveassistant.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HiveAssistantApplicationTests {

    /*@Autowired
    private UserDao userDao;

    @Autowired
    private StatementDao statementDao;

    @Autowired
    private MongoTemplate mongoTemplate;*/

    @Test
    public void contextLoads() {

        /*List<UserInfo> userInfos = userDao.findByLoginNameLikeAndId("", 1, PageRequest.of(0, 20)).getContent();

        for (UserInfo user : userInfos) {
            System.out.println(user.getLoginName());
        }*/

        /*int count = userDao.countByLoginNameLikeAndId("", 1);
        System.out.println("count:" + count);

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(1).and("loginName").regex(".*?.*"));
        count = (int)mongoTemplate.count(query,UserInfo.class);
        System.out.println("count:" + count);*/


        /*List<StatementInfo> lists = statementDao.findByContentLikeAndUserId("","",PageRequest.of(0,78)).getContent();

        for (StatementInfo info : lists) {
            System.out.println(info.getId());
        }*/
    }

}
