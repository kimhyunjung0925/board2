package com.study.board2.Controller;

import com.study.board2.dto.UserDto;
import com.study.board2.entity.UserEntity;
import com.study.board2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    //생성자주입
    private final UserService userService;

    //회원가입창
    @GetMapping("/join")
    public String joinForm(){
        return "user/join";
    }

    //회원가입처리
    @PostMapping("/join")
    public String join(@ModelAttribute UserDto dto){
        userService.join(dto);
        return "user/login";
    }

    //아이디체크
    @PostMapping("/uid-check")
    public @ResponseBody int emailCheck(@RequestParam("uid") String uid){
        int CheckResult = userService.uidCheck(uid);
        return CheckResult;
    }

    //로그인창
    @GetMapping("/login")
    public String loginForm(){
        return "user/login";
    }

    //로그인처리
    @PostMapping("/login")
    public String login(@ModelAttribute UserDto dto, HttpSession session, Model model){
        UserDto loginResult = userService.login(dto);

        if(loginResult != null) {
            //로그인성공
            session.setAttribute("loginIuser", loginResult.getIuser());
            session.setAttribute("loginUid", loginResult.getUid());
            return "main";
        } else {
            //로그인실패
            model.addAttribute("message","아이디나 비밀번호가 틀렸습니다 다시시도해주세요. ");
            model.addAttribute("searchUrl","/user/login");
            return "message";
        }
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession hs){
        hs.invalidate();
        return "main";
    }

    //회원리스트
    @GetMapping("/list")
    public String FindAll(Model model){
        List<UserDto> userDtoList = userService.findAll();
        model.addAttribute("userList", userDtoList);
        return "user/userList";
    }

    //회원상세보기
    @GetMapping("/{iuser}")
    public String FindById(@PathVariable Integer iuser, Model model){
        UserDto dto = userService.findByIuser(iuser);
        model.addAttribute("user", dto);

        return "user/detail";
    }

    //회원삭제(리스트에서 삭제처리)
    @GetMapping("/delete/{iuser}")
    public String deleteByIuser(@PathVariable Integer iuser){
        userService.deleteByIuser(iuser);

        return "redirect:/user/list";
    }

    //회원정보수정창
    @GetMapping("/update")
    public String UpdateForm(HttpSession hs, Model model){
        Integer myIuser = (Integer) hs.getAttribute("loginIuser");
        UserDto dto = userService.updateForm(myIuser);
        model.addAttribute("updateUser",dto);
        return "user/update";
    }

    //회원정보수정처리
    @PostMapping("/update")
    public String update(@ModelAttribute UserDto dto){
        userService.update(dto);
        return "redirect:/user/" + dto.getIuser();
    }

    //회원탈퇴 폼
    @GetMapping("/leave/{iuser}")
    public String leavefrm(@PathVariable Integer iuser, Model model){
        UserDto dto = userService.findByIuser(iuser);
        model.addAttribute("user", dto);
        return "user/leave";
    }

    //회원탈퇴 처리
    @PostMapping("/leave")
    public String leave(@ModelAttribute UserDto dto, Model model){
        userService.deleteByIuser(dto.getIuser());

        model.addAttribute("message","회원탈퇴처리가 완료되었습니다.");
        model.addAttribute("searchUrl","/user/login");
        return "message";
    }

}
