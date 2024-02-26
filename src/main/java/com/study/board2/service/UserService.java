package com.study.board2.service;

import com.study.board2.dto.UserDto;
import com.study.board2.entity.UserEntity;
import com.study.board2.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원가입
    public void join(UserDto userDto) {
        //1.dto ->entity변환
        UserEntity userEntity = UserEntity.toUser(userDto);
        //2.repository save 메서드호출
        userRepository.save(userEntity);
    }

    //아이디중복체크
    public int uidCheck(String uid) {
        Optional<UserEntity> byUid = userRepository.findByUid(uid);

        if (uid.isEmpty()) {
            //빈값일 때
            return 2;
        } else if(byUid.isPresent()){
            //조회결과가 있는 경우 = 사용할 수 없음
            return 0;
        } else {
            //조회결과가 없는 경우 = 사용할 수 있음
            return 1;
        }
    }

    //로그인
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

    //회원리스트
    public List<UserDto> findAll(){
        List<UserEntity> all = userRepository.findAll();
        List<UserDto> dtoList = new ArrayList<>();

        for(UserEntity entity : all){
            dtoList.add(UserDto.toUserDto(entity));
        }

        return dtoList;
    }

    //특정iuser 회원정보(상세보기)
    public UserDto findByIuser(Integer iuser) {
        Optional<UserEntity> byIuser = userRepository.findByIuser(iuser);

        if(byIuser.isPresent()){
            return UserDto.toUserDto(byIuser.get());
        } else {
            return null;
        }
    }

    //회원삭제처리, 탈퇴하기
    public void deleteByIuser(Integer iuser) {;
        userRepository.deleteById(Long.valueOf(iuser));
    }

    //회원정보수정 폼
    public UserDto updateForm(Integer iuser) {
        Optional<UserEntity> byIuser = userRepository.findByIuser(iuser);
        if(byIuser.isPresent()){
            return UserDto.toUserDto(byIuser.get());
        } else {
            return null;
        }
    }

    //회원수정처리
    public void update(UserDto dto) {
        userRepository.save(UserEntity.toUpdateEntity(dto));
    }




}
