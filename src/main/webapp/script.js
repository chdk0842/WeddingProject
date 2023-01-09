function chkForm() {
	var f = document.frm;

	if (f.title.value == '') {
		alert("제목을 입력해주세요.");
		return false;
	}
	if (f.user_id.value == '') {
		alert("아이디 입력해주세요.");
		return false;
	}
	if (f.content.value == '') {
		alert("내용을 입력해주세요.");
		return false;
	}

	f.submit(); //폼태그 전송

}

function logForm() {
	var f = document.frm;

	if (f.user_name.value == '') {
		alert("이름을 입력해주세요.");
		return false;
	}
	if (f.user_id.value == '') {
		alert("아이디을 입력해주세요.");
		return false;
	}
	if (f.user_pw.value == '') {
		alert("비번을 입력해주세요.");
		return false;
	}

	f.submit(); //폼태그 전송

}

function chkDelete(list_no, user_pw) {
	let result = confirm("정말 삭제하시겠습니까?");


	if (result) {
	    var pt = prompt("비밀번호를 입력해주세요", "");
        
		if (pt == user_pw) {
			alert("삭제 되었습니다.");
			const url = location.origin;
			location.href = url + "/Wedding/delete?list_no=" + list_no;

		} else {
			alert("비밀번호가 틀렸습니다.");
			return false;
		}

	} else {
		alert("취소 되었습니다.");
		return false;
	}

}
