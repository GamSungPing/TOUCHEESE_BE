// package com.rocket.toucheese_be.global.fcm;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import jakarta.annotation.PostConstruct;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.ClassPathResource;

// import java.io.IOException;
// import java.io.InputStream;

// @Configuration
// public class FirebaseConfig {

//     /**
//      * 애플리케이션 실행 시 Firebase 초기화
//      */
//     @PostConstruct
//     public void initialize() throws IOException {
//         // ClassPath 사용해 resources 폴더에 있는 json 키 파일 로드
//         ClassPathResource resource = new ClassPathResource("firebase/firebase_service_key.json");

//         // json 키 파일 존재 여부 확인
//         if(!resource.exists()) {
//             throw new IOException("!!! Firebase service key file not found at path !!!");
//         }

//         // 파일을 InputStream으로 직접 로드
//         try (InputStream serviceAccount = resource.getInputStream()) {
//             FirebaseOptions options = FirebaseOptions.builder()
//                     .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                     .build();

//             // Firebase 앱이 초기화 되지 않은 경우에 강제 초기화
//             if (FirebaseApp.getApps().isEmpty()) {
//                 FirebaseApp.initializeApp(options);
//             }
//         }
//     }
// }
