package com.rocket.toucheese_be.domain.member.service;

import com.google.gson.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppleService {

    private static final String TOKEN_VALUE_DELIMITER = "\\.";
    private static final String MODULUS = "n";
    private static final String EXPONENT = "e";
    private static final int QUOTES = 1;
    private static final int POSITIVE_NUMBER = 1;

    private PublicKey makePublicKey(String accessToken, JsonArray publicKeyList) {
        String[] decodeArray = accessToken.split(TOKEN_VALUE_DELIMITER);
        String header = new String(Base64.getDecoder().decode(decodeArray[0].replaceFirst("Bearer ", "")));

        JsonElement kid = ((JsonObject) JsonParser.parseString(header)).get("kid");
        JsonElement alg = ((JsonObject) JsonParser.parseString(header)).get("alg");
        JsonObject matchingPublicKey = findMatchingPublicKey(publicKeyList, kid, alg);

        if (Objects.isNull(matchingPublicKey)) {
            throw new IllegalArgumentException();
        }

        return getPublicKey(matchingPublicKey);
    }

    private JsonObject findMatchingPublicKey(JsonArray publicKeyList, JsonElement kid, JsonElement alg) {
        for (JsonElement publicKey : publicKeyList) {
            JsonObject publicKeyObject = publicKey.getAsJsonObject();
            JsonElement publicKid = publicKeyObject.get("kid");
            JsonElement publicAlg = publicKeyObject.get("alg");

            if (Objects.equals(kid, publicKid) && Objects.equals(alg, publicAlg)) {
                return publicKeyObject;
            }
        }

        return null;
    }

    private PublicKey getPublicKey(JsonObject object) {
        try {
            String modulus = object.get(MODULUS).toString();
            String exponent = object.get(EXPONENT).toString();

            byte[] modulusBytes = Base64.getUrlDecoder().decode(modulus.substring(QUOTES, modulus.length() - QUOTES));
            byte[] exponentBytes = Base64.getUrlDecoder().decode(exponent.substring(QUOTES, exponent.length() - QUOTES));

            BigInteger modulusValue = new BigInteger(POSITIVE_NUMBER, modulusBytes);
            BigInteger exponentValue = new BigInteger(POSITIVE_NUMBER, exponentBytes);

            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulusValue, exponentValue);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException exception) {
            throw new IllegalStateException();
        }
    }

    private JsonArray getApplePublicKeys() {
        HttpURLConnection connection = sendHttpRequest();
        StringBuilder result = getHttpResponse(connection);
        JsonObject keys = (JsonObject) JsonParser.parseString(result.toString());
        return (JsonArray) keys.get("keys");
    }

    private HttpURLConnection sendHttpRequest() {
        try {
            URL url = new URL("https://appleid.apple.com/auth/keys");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.GET.name());
            return connection;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private StringBuilder getHttpResponse(HttpURLConnection connection) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return splitHttpResponse(bufferedReader);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private StringBuilder splitHttpResponse(BufferedReader bufferedReader) {
        try {
            StringBuilder result = new StringBuilder();

            String line;
            while (Objects.nonNull(line = bufferedReader.readLine())) {
                result.append(line);
            }
            bufferedReader.close();

            return result;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getAppleData(String socialAccessToken) {
        JsonArray publicKeyList = getApplePublicKeys();
        PublicKey publicKey = makePublicKey(socialAccessToken, publicKeyList);

        Claims userInfo = Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(socialAccessToken.replaceFirst("Bearer ", ""))
                .getPayload();

        JsonObject userInfoObject = (JsonObject) JsonParser.parseString(new Gson().toJson(userInfo));
        return userInfoObject.get("sub").getAsString();
    }
}
