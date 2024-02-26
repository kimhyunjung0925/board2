
const leaveFrmElem = document.querySelector('#leave-frm');
leaveFrmElem.addEventListener('submit', (e) => {

    const pwChk = document.querySelector('#pwChk').value;
    const oriPw = document.querySelector('#oriPwChk').value;

    if (pwChk !== oriPw) {
        alert("비밀번호가 틀렸습니다. 다시 확인해 주세요.");
        e.preventDefault();
        return;
    }  else {
        if (!confirm("정말로 탈퇴 하시겠습니까?")) {
            // 취소(아니오) 버튼 클릭 시 이벤트
            e.preventDefault();
            // } else {
            //     // 확인(예) 버튼 클릭 시 이벤트 = 그냥 submit처리
            //
        }
    }
});