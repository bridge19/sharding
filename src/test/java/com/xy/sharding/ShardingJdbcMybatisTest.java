package com.xy.sharding;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xy.sharding.entity.Student;
import com.xy.sharding.entity.User;
import com.xy.sharding.service.StudentService;
import com.xy.sharding.service.UserService;
 
/**
 * ���Էֿ�ֱ����
 * @author liuyazhuang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath*:spring-database.xml", "classpath*:spring-sharding.xml" })  
public class ShardingJdbcMybatisTest {  
 
    @Resource  
    public UserService userService;  
      
    @Resource  
    public StudentService studentService;  
 
    @Test  
    public void testUserInsert() {  
        User u = new User();  
        u.setUserId(11);  
        u.setAge(25);  
        u.setName("github");  
        Assert.assertEquals(userService.insert(u), true);  
    }  
      
    @Test  
    public void testStudentInsert() {  
        Student student = new Student();  
        student.setStudentId(30);  
        student.setAge(23);  
        student.setName("xuyong");  
        Assert.assertEquals(studentService.insert(student), true);  
    }  
 
    @Test  
    public void testFindAll(){  
        List<User> users = userService.findAll();  
        if(null != users && !users.isEmpty()){  
            for(User u :users){  
                System.out.println(u);  
            }  
        }  
    }  
      
    @Test  
    public void testSQLIN(){  
        List<User> users = userService.findByUserIds(Arrays.asList(1));  
        if(null != users && !users.isEmpty()){  
            for(User u :users){  
                System.out.println(u);  
            }  
        }  
    }  
      
    @Test  
    public void testTransactionTestSucess(){  
        userService.transactionTestSucess();  
    }  
      
    @Test(expected = IllegalAccessException.class)  
    public void testTransactionTestFailure() throws IllegalAccessException{  
        userService.transactionTestFailure();  
    }  
}  
