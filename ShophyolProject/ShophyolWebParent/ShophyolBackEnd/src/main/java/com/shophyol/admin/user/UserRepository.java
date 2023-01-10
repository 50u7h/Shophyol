package com.shophyol.admin.user;

import org.springframework.data.repository.CrudRepository;

import com.shophyol.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
