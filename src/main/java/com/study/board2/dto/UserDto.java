package com.study.board2.dto;

import com.study.board2.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor  //기본생성자 자동
@AllArgsConstructor //필드를 매개변수로 하는 생성자 자동
@ToString   //필드값출력
public class UserDto {
    private Integer iuser;
    private String uid;
    private String upw;
    private String unm;
    private String uemail;

    public static UserDto toUserDto(UserEntity entity){
        UserDto dto = new UserDto();
        dto.setIuser(entity.getIuser());

        dto.setUid(entity.getUid());
        dto.setUpw(entity.getUpw());
        dto.setUemail(entity.getUemail());
        dto.setUnm(entity.getUnm());
        return dto;
    }
}