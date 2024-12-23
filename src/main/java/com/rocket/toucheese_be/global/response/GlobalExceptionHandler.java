package com.rocket.toucheese_be.global.response;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * CustomException 처리
     * ErrorCode에 정의된 HttpStatus와 메시지를 클라이언트에 반환
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        // ErrorResponse 생성
        Response errorResponse = Response.of(
                errorCode.getHttpStatus().value(),
                errorCode.getMessage(),
                null
        );

        // ErrorCode에 정의된 HTTP 상태와 함께 반환
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    /**
     * 기타 예외 처리
     * 예상하지 못한 예외가 발생했을 때 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGenericException(Exception ex) {
        Response errorResponse = Response.of(
                500,
                "서버 오류",
                null
        );

        // HTTP 상태 500 반환
        return ResponseEntity.status(500).body(errorResponse);
    }
}