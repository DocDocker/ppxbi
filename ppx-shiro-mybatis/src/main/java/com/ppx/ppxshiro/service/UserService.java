package com.ppx.ppxshiro.service;


import com.ppx.ppxshiro.domain.User;

/**
 * @功能描述：TODO
 * @创建日期: 2019/2/22 20:28
 */
public interface UserService {
    User findByName(String name);
    User findById(Integer id);
}
