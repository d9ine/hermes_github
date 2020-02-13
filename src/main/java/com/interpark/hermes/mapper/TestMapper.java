package com.interpark.hermes.mapper;

import com.interpark.hermes.models.JPATest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    public JPATest selectJPATest(JPATest jpaTest) throws Exception;
    public Integer insertJPATest(JPATest jpaTest) throws Exception;
}
