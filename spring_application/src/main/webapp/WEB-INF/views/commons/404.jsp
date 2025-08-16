<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
alert("당신은 권한이 없습니다.");

if (window.opener) { // 팝업창일 경우
    window.opener.location.reload();
    window.close();
} else { // 일반 창일 경우
    window.history.back(); // 뒤로 가기
    // 또는 location.href = "/"; // 홈으로 이동
}
</script>