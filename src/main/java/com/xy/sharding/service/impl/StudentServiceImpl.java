package com.xy.sharding.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xy.sharding.entity.Student;
import com.xy.sharding.mapper.StudentMapper;
import com.xy.sharding.service.StudentService;

@Service  
public class StudentServiceImpl implements StudentService{  
      
    @Resource  
    public StudentMapper studentMapper;  
  
    public boolean insert(Student student) {  
        return studentMapper.insert(student) > 0 ? true : false;  
    }  
  
}  
