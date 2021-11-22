package com.wolken.Wolken.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wolken.Wolken.entity.UserEntity;

public interface UserRepo extends JpaRepositoryImplementation<UserEntity, Integer>{

}
