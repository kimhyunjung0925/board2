package com.study.board2.repository;

import com.study.board2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //ID회원정보조회
    Optional<UserEntity> findByUid(String uid);

    //iuser 회원정보조회
    Optional<UserEntity> findByIuser(Integer iuser);

}
