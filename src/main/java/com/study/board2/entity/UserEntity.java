package com.study.board2.entity;

import com.study.board2.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name="t_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto_increment
    private Integer iuser;

    @Column(unique = true)  //unique 제약조건추가
    private String uid;
    @Column
    private String upw;
    @Column
    private String unm;
    @Column
    private String uemail;

    public static UserEntity toUser(UserDto dto){
        UserEntity entity = new UserEntity();
        entity.setUid(dto.getUid());
        entity.setUpw(dto.getUpw());
        entity.setUnm(dto.getUnm());
        entity.setUemail(dto.getUemail());
        return entity;
    }

    public static UserEntity toUpdateEntity(UserDto dto){
        UserEntity entity = new UserEntity();
        entity.setIuser(dto.getIuser());
        entity.setUid(dto.getUid());
        entity.setUpw(dto.getUpw());
        entity.setUnm(dto.getUnm());
        entity.setUemail(dto.getUemail());
        return entity;
    }
}
