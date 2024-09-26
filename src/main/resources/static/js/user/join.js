{
    let idChkState = 3; //3는 기본 중복체크 안함
    const joinFrmElem = document.querySelector('#join-frm');
    const idChkBtn = document.querySelector('#idChkBtn');

    const uidcheckResult = document.getElementById("id-check-result");
    //TODO 비밀번호 형식, 이메일 형식 체크 예정
    const upwcheckResult = document.getElementById("pw-check-result");
    const uemailcheckResult = document.getElementById("email-check-result");

    idChkBtn.addEventListener('click', () => {
        const uid = document.getElementById("uid").value;

        $.ajax({
            type: "post",
            url: "/user/uid-check",
            data: {
                "uid": uid
            },
            success: function (res) {
                idChkState = res;
                console.log(res);
                if (res == 1) {
                    //사용가능
                    uidcheckResult.innerHTML = "사용가능한 아이디 입니다"
                    uidcheckResult.style.color = "black";
                } else if (res == 2) {
                    //아이디 value 빈값인 상태
                    uidcheckResult.innerHTML = "아이디를 입력해 주세요."
                    uidcheckResult.style.color = "red";
                } else {
                    //이미 사용중 id
                    uidcheckResult.innerHTML = "이미사용중인 아이디 입니다"
                    uidcheckResult.style.color = "red";
                }
            },
            error: function (err) {
                console.log("ajax 통신 에러", err);
            }
        });
    });

    <!--    id 중복체크 한 후 아이디 바꿔서 입력후 바로 회원가입 눌렀을 때 오류 방지-->
    const uidElem = document.getElementById("uid");
    uidElem.addEventListener('input', (e) => {
        idChkState = 3;
    });

    <!--    회원가입 처리    -->
    joinFrmElem.addEventListener('submit', (e) => {

        const uid = document.getElementById("uid").value;
        const upw = document.getElementById("upw").value;
        const uemail = document.getElementById("uemail").value;
        const unm = document.getElementById("unm").value;

        //1.아이디 체크
        if (uid === "" || uid === null) {
            //아이디 빈값일 때
            alert("아이디를 입력해 주세요.");
            //submit 행위 중단 처리
            e.preventDefault();
            return;
        } else {
            console.log(idChkState);
            //아이디가 있을 때
            if (idChkState === 3) {
                //아이디 중복체크 안했을 때
                alert('아이디 중복 체크를 해주세요!');
                e.preventDefault();
                return;
            } else if (idChkState === 0) {
                //아이디중복
                alert('다른 아이디를 사용해 주세요!');
                e.preventDefault();
                return;
            }
        }

        if (upw === "" || upw === null) {
            //2.pw체크
            alert("비밀번호를 입력해 주세요");
            e.preventDefault();
            return;
        }

        //3.email체크
        if (uemail === "" || uemail === null) {
            alert("이메일을 입력해 주세요.");
            e.preventDefault();
            return;
        }

        //4.이름체크
        if (unm === "" || unm === null) {
            alert("이름을 입력해 주세요.");
            e.preventDefault();
            return;
        }
    });
}