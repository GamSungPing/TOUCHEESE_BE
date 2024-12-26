package com.rocket.toucheese_be.global.fcm;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class FcmService {

    // FCM 메시지 전송 메소드 - targetToken은 클라이언트가 보내준 디바이스 토큰 (Device에 저장되어 있음)
    public void sendPushMsg(String targetToken, PushMsg pushMsg, String studioName) throws IOException, FirebaseMessagingException {

        // 메시지 객체 형식에 맞추어 푸시 메시지 빌드
        Message message = Message.builder()
                .setNotification(
                        new Notification(
                                pushMsg.getTitle(),
                                studioName + pushMsg.getBody())
                )
                .setToken(targetToken) // 클라이언트 없을 때는 .setTopic("test_topic") 로 놓고 디버깅
                .setApnsConfig(ApnsConfig.builder() // 앱 별 세부 설정 (옵션) - iOS
                        .setAps(Aps.builder()
                                .setBadge(1) // 알림 뱃지 설정
                                .build())
                        .build())
                .setAndroidConfig(AndroidConfig.builder() // 앱 별 세부 설정 (옵션) - Android
                        .setPriority(AndroidConfig.Priority.HIGH) // 전송 우선순위 설정
                        .build())
                .build();

        // Firebase 서버에 해당 메시지 전송 요청 및 확인용 출력
        String response = FirebaseMessaging.getInstance().send(message);
        log.info("푸시 메시지 전송 성공: " + response);
    }
}
