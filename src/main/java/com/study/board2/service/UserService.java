package com.study.board2.service;

import com.study.board2.dto.UserDto;
import com.study.board2.entity.UserEntity;
import com.study.board2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public  void deleteByIuser(Long iuser) {
        userRepository.deleteById(iuser);
    }

    public void update(UserDto dto) {
        userRepository.save(UserEntity.toUpdateEntity(dto));
    }

    //회원정보수정
    public UserDto updateForm(Integer iuser) {
        Optional<UserEntity> byIuser = userRepository.findByIuser(iuser);
        if(byIuser.isPresent()){
            return UserDto.toUserDto(byIuser.get());
        } else {
            return null;
        }
    }

    public void join(UserDto userDto) {
        //1.dto ->entity변환
        UserEntity userEntity = UserEntity.toUser(userDto);
        //2.repository save 메서드호출
        userRepository.save(userEntity);
    }

    public UserDto login(UserDto userDto) {
        //회원입력내용 확인(ID,PW)
        Optional<UserEntity> byUid = userRepository.findByUid(userDto.getUid());
        if(byUid.isPresent()){
            //조회결과 있음
            UserEntity userEntity = byUid.get();
            if(userEntity.getUpw().equals(userDto.getUpw())){
                //비밀번호일치
                //entity -> dto 변환
                UserDto dto = UserDto.toUserDto(userEntity);
                return dto;
            } else {
                //비밀번호틀림
                return null;
            }
        } else {
            //조회결과 없음
            return null;
        }
    }

    public List<UserDto> findAll(){
        List<UserEntity> all = userRepository.findAll();
        List<UserDto> dtoList = new ArrayList<>();

        for(UserEntity entity : all){
            dtoList.add(UserDto.toUserDto(entity));
        }

        return dtoList;
    }

    public UserDto findByIuser(Integer iuser) {
        Optional<UserEntity> byIuser = userRepository.findByIuser(iuser);

        if(byIuser.isPresent()){
            return UserDto.toUserDto(byIuser.get());
        } else {
            return null;
        }

    }
}
