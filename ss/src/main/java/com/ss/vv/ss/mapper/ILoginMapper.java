package com.ss.vv.ss.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.vv.common.IOperations;
import com.ss.vv.ss.domain.User;

@Repository
  public interface ILoginMapper extends IOperations<User, User> {
  public User findByUidAndPassword(@Param("id")int uId,@Param("password")String password);
}
