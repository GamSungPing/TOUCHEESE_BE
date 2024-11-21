INSERT INTO region (name) VALUES ('강남구');/* 1 */
INSERT INTO region (name) VALUES ('서초구');/* 2 */
INSERT INTO region (name) VALUES ('송파구');/* 3 */
INSERT INTO region (name) VALUES ('강서구');/* 4 */
INSERT INTO region (name) VALUES ('마포구');/* 5 */
INSERT INTO region (name) VALUES ('영등포');/* 6 */
INSERT INTO region (name) VALUES ('강북구');/* 7 */
INSERT INTO region (name) VALUES ('용산구');/* 8 */
INSERT INTO region (name) VALUES ('성동구');/* 9 */


INSERT INTO concept (name) VALUES ('플래쉬 터지는 느낌');
INSERT INTO concept (name) VALUES ('생동감 있는 실물 느낌');
INSERT INTO concept (name) VALUES ('블랙/블루 배우느낌');
INSERT INTO concept (name) VALUES ('내추럴 화보 느낌');
INSERT INTO concept (name) VALUES ('수체화 그림체 느낌');
INSERT INTO concept (name) VALUES ('화려한 느낌');


/*
 플래쉬 터지는 느낌
 */
INSERT INTO studio (name, profile_price, region_id) VALUES ('아워유스', 99000, 5);
INSERT INTO studio (name, profile_price, region_id) VALUES ('허쉬스튜디오', 120000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('레코디드(홍대)', 50000, 5);
INSERT INTO studio (name, profile_price, region_id) VALUES ('레코디드(강남)', 50000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('셀리온', 80000, 5);

/*
생동감 있는 실물 느낌
*/
INSERT INTO studio (name, profile_price, region_id) VALUES ('시현하다-강남 오리지널', 84000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('시현하다-성수 플래그십', 84000, 9);
INSERT INTO studio (name, profile_price, region_id) VALUES ('시현하다-홍대 스페이스', 84000, 5);
INSERT INTO studio (name, profile_price, region_id) VALUES ('소희모먼트(sohoe moment)- 용산점', 100000, 8);
INSERT INTO studio (name, profile_price, region_id) VALUES ('소희모먼트(sohoe moment)- 신사점', 120000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('오쓰리스튜디오(O3 STUDIO)', 85000, 1);


/*
 블랙/블루 배우느낌
 */
INSERT INTO studio (name, profile_price, region_id) VALUES ('리제이스튜디오', 210000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('유프스튜디오', 170000, 9);
INSERT INTO studio (name, profile_price, region_id) VALUES ('타호스튜디오', 60000, 4);
INSERT INTO studio (name, profile_price, region_id) VALUES ('스튜디오디온', 190000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('아인츠스튜디오', 250000, 5);
INSERT INTO studio (name, profile_price, region_id) VALUES ('스튜디오원픽(이월점)', 200000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('서래스튜디오', 250000, 2);
INSERT INTO studio (name, profile_price, region_id) VALUES ('스튜디오 코스모스', 140000, 2);
INSERT INTO studio (name, profile_price, region_id) VALUES ('옥토비스튜디오', 190000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('피노스튜디오', 470000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('르브아스튜디오', 200000, 2);
INSERT INTO studio (name, profile_price, region_id) VALUES ('스튜디오아이엔씨', 110000, 2);
INSERT INTO studio (name, profile_price, region_id) VALUES ('파스타스튜디오', 200000, 5);
INSERT INTO studio (name, profile_price, region_id) VALUES ('뮬라이트스튜디오', 290000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('스튜디오 알로', 120000, 6);
INSERT INTO studio (name, profile_price, region_id) VALUES ('시노그라피', 250000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('최현민 스튜디오', 80000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('라라트 스튜디오', 180000, 2);


/*
내추럴 화보 느낌
 */
INSERT INTO studio (name, profile_price, region_id) VALUES ('프롬선스튜디오', 120000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('FF STUDIO', 230000, 6);
INSERT INTO studio (name, profile_price, region_id) VALUES ('스튜디오피플', 90000, 5);

/*
 수체화 그림체 느낌
 */
INSERT INTO studio (name, profile_price, region_id) VALUES ('오해피데이스튜디오', 75000, 1);

/*
 화려한 느낌
 */
INSERT INTO studio (name, profile_price, region_id) VALUES ('그믐달 홍대점', 65000, 5);
INSERT INTO studio (name, profile_price, region_id) VALUES ('그믐달 청담점', 65000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('버터플라이하우스', 60000, 5);
INSERT INTO studio (name, profile_price, region_id) VALUES ('스쿠피스튜디오', 55000, 1);
INSERT INTO studio (name, profile_price, region_id) VALUES ('류앤라이크', 70000, 1);


UPDATE studio
SET price_category =
        CASE
            WHEN profile_price < 100000 THEN 'LOW'
            WHEN profile_price < 200000 THEN 'MEDIUM'
            ELSE 'HIGH'
            END
WHERE price_category IS NULL;


INSERT INTO member (name) VALUES ('member1');
INSERT INTO member (name) VALUES ('member2');
INSERT INTO member (name) VALUES ('member3');
INSERT INTO member (name) VALUES ('member4');
INSERT INTO member (name) VALUES ('member5');
INSERT INTO member (name) VALUES ('member6');
INSERT INTO member (name) VALUES ('member7');
INSERT INTO member (name) VALUES ('member8');
INSERT INTO member (name) VALUES ('member9');
INSERT INTO member (name) VALUES ('member10');
INSERT INTO member (name) VALUES ('member11');
INSERT INTO member (name) VALUES ('member12');
INSERT INTO member (name) VALUES ('member13');
INSERT INTO member (name) VALUES ('member14');
INSERT INTO member (name) VALUES ('member15');
INSERT INTO member (name) VALUES ('member16');
INSERT INTO member (name) VALUES ('member17');
INSERT INTO member (name) VALUES ('member18');
INSERT INTO member (name) VALUES ('member19');
INSERT INTO member (name) VALUES ('member20');
INSERT INTO member (name) VALUES ('member21');
INSERT INTO member (name) VALUES ('member22');
INSERT INTO member (name) VALUES ('member23');

INSERT INTO studio_concept (concept_id, studio_id) VALUES (1, 1);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (1, 2);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (1, 3);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (1, 4);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (1, 5);

INSERT INTO studio_concept (concept_id, studio_id) VALUES (2, 6);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (2, 7);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (2, 8);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (2, 9);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (2, 10);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (2, 11);

INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 16);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 17);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 18);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 19);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 20);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 21);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 22);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 23);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 24);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 25);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 26);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 27);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 28);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (3, 29);

INSERT INTO studio_concept (concept_id, studio_id) VALUES (4, 12);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (4, 13);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (4, 14);

INSERT INTO studio_concept (concept_id, studio_id) VALUES (5, 15);

INSERT INTO studio_concept (concept_id, studio_id) VALUES (6, 30);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (6, 31);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (6, 32);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (6, 33);
INSERT INTO studio_concept (concept_id, studio_id) VALUES (6, 34);


INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (2, 1, 5, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (3, 2, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (5, 3, 5, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (10, 4, 3, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (12, 5, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (1, 6, 3, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (8, 7, 5, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (6, 8, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (4, 9, 2, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (14, 10, 5, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (19, 11, 3, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (20, 12, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (25, 13, 5, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (30, 14, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (32, 15, 5, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (17, 16, 3, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (22, 17, 2, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (13, 18, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (11, 19, 3, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (7, 20, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (18, 21, 5, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (24, 22, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (9, 23, 5, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (3, 1, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (15, 2, 2, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (28, 3, 5, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (26, 4, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (21, 5, 3, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (23, 6, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (2, 7, 5, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (27, 8, 3, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (31, 9, 4, NOW());
INSERT INTO rating (studio_id, member_id, rating, created_at) VALUES (33, 10, 5, NOW());

# 1. 아워유스
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (1, 'https://i.imgur.com/W187rjj.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (1, 'https://i.imgur.com/T1V4DMh.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (1, 'https://i.imgur.com/LMluDsI.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (1, 'https://i.imgur.com/9yomG3v.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (1, 'https://i.imgur.com/eIbQ1tI.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (1, 'https://i.imgur.com/R3d1Oz3.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (1, 'https://i.imgur.com/vupmDC6.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (1, 'https://i.imgur.com/nfC2nZC.jpeg');

# 2. 허쉬스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (2, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (2, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (2, 'https://i.imgur.com/saTswyy.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (2, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (2, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (2, 'https://i.imgur.com/Nhzuiq0.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (2, 'https://i.imgur.com/Fx9JRvm.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (2, 'https://i.imgur.com/xtxrwPZ.jpeg');

# 3. 레코디드(홍대)
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (3, 'https://i.imgur.com/42Ao10P.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (3, 'https://i.imgur.com/bf5Gtez.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (3, 'https://i.imgur.com/fR0nHDr.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (3, 'https://i.imgur.com/BojxSWj.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (3, 'https://i.imgur.com/qDFMOpp.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (3, 'https://i.imgur.com/OsIaN2t.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (3, 'https://i.imgur.com/RWSpSHR.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (3, 'https://i.imgur.com/vdnjhKQ.jpeg');

# 4. 레코디드(강남)
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (4, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (4, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (4, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (4, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (4, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (4, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (4, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (4, 'https://i.imgur.com/Uw5nNHQ.png');

# 5. 셀리온
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (5, 'https://i.imgur.com/T0ZvNOL.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (5, 'https://i.imgur.com/lAuSaVp.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (5, 'https://i.imgur.com/SuD7P1V.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (5, 'https://i.imgur.com/gxqdlKZ.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (5, 'https://i.imgur.com/mUvOTCh.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (5, 'https://i.imgur.com/LOZ6qmf.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (5, 'https://i.imgur.com/HSEHrDd.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (5, 'https://i.imgur.com/P6mgUQl.jpeg');

# 6. 시현하다 강남 오리지널
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (6, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (6, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (6, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (6, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (6, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (6, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (6, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (6, 'https://i.imgur.com/Uw5nNHQ.png');

# 7. 시현하다 성수 플래그십
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (7, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (7, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (7, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (7, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (7, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (7, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (7, 'https://i.imgur.com/Uw5nNHQ.png');

# 8. 시현하다 홍대 스페이스
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (8, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (8, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (8, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (8, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (8, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (8, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (8, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (8, 'https://i.imgur.com/Uw5nNHQ.png');

# 9. 소희모먼트 용산점
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (9, 'https://i.imgur.com/7MkF8QM.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (9, 'https://i.imgur.com/4P0ErdY.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (9, 'https://i.imgur.com/qx1VZNM.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (9, 'https://i.imgur.com/1eNB849.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (9, 'https://i.imgur.com/X2KBuyX.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (9, 'https://i.imgur.com/Usi4qij.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (9, 'https://i.imgur.com/fzagf8N.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (9, 'https://i.imgur.com/3LiTxJ6.jpeg');

# 10. 소희모먼트 신사점
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (10, 'https://i.imgur.com/7MkF8QM.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (10, 'https://i.imgur.com/4P0ErdY.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (10, 'https://i.imgur.com/qx1VZNM.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (10, 'https://i.imgur.com/1eNB849.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (10, 'https://i.imgur.com/X2KBuyX.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (10, 'https://i.imgur.com/Usi4qij.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (10, 'https://i.imgur.com/fzagf8N.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (10, 'https://i.imgur.com/3LiTxJ6.jpeg');

# 11. 오쓰리스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (11, 'https://i.imgur.com/XwofRAp.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (11, 'https://i.imgur.com/kfDyrH2.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (11, 'https://i.imgur.com/uy5LQ4k.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (11, 'https://i.imgur.com/U7AHgpa.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (11, 'https://i.imgur.com/UX3fSID.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (11, 'https://i.imgur.com/VkQBoL0.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (11, 'https://i.imgur.com/qesEBQ1.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (11, 'https://i.imgur.com/U0aOZ0U.jpeg');

# 12. 리제이 스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (12, 'https://i.imgur.com/ItWR5sD.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (12, 'https://i.imgur.com/V1aLKok.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (12, 'https://i.imgur.com/YEdn7IH.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (12, 'https://i.imgur.com/OkjqvUx.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (12, 'https://i.imgur.com/bo0FgPX.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (12, 'https://i.imgur.com/67SUT6B.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (12, 'https://i.imgur.com/aEpTzqa.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (12, 'https://i.imgur.com/bk1WARb.jpeg');

# 13. 유프스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (13, 'https://i.imgur.com/rwYNrcL.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (13, 'https://i.imgur.com/cPzLGPb.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (13, 'https://i.imgur.com/BbNTNnU.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (13, 'https://i.imgur.com/NKzejVf.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (13, 'https://i.imgur.com/BSXryF1.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (13, 'https://i.imgur.com/5Nj6Zou.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (13, 'https://i.imgur.com/sqSo0sX.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (13, 'https://i.imgur.com/HKPZQOy.jpeg');

# 14. 타호스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (14, 'https://i.imgur.com/WMLU31q.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (14, 'https://i.imgur.com/HY2RWy0.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (14, 'https://i.imgur.com/XNfDVEP.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (14, 'https://i.imgur.com/pEhACww.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (14, 'https://i.imgur.com/UtG27pU.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (14, 'https://i.imgur.com/AUnMVJh.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (14, 'https://i.imgur.com/6XP5SYX.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (14, 'https://i.imgur.com/uML4vEI.jpeg');

# 15. 스튜디오디온
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (15, 'https://i.imgur.com/e5G9q3c.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (15, 'https://i.imgur.com/xa6bXIW.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (15, 'https://i.imgur.com/NFc7cCJ.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (15, 'https://i.imgur.com/uaZy5rU.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (15, 'https://i.imgur.com/z5XQsZ2.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (15, 'https://i.imgur.com/rwHRaia.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (15, 'https://i.imgur.com/nDw2OM1.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (15, 'https://i.imgur.com/ZfBo7uv.jpeg');

# 16. 아인츠스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (16, 'https://i.imgur.com/mDc5kUY.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (16, 'https://i.imgur.com/urvTsVc.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (16, 'https://i.imgur.com/azCRwNa.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (16, 'https://i.imgur.com/o1x15vb.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (16, 'https://i.imgur.com/n4qNy5l.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (16, 'https://i.imgur.com/adagGPJ.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (16, 'https://i.imgur.com/86ZZARF.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (16, 'https://i.imgur.com/sIepKcn.jpeg');

# 17. 스튜디오 원필 이월점
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (17, 'https://i.imgur.com/Uw5nNHQ.png');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (17, 'https://i.imgur.com/iDQcTkl.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (17, 'https://i.imgur.com/Wrvs1sh.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (17, 'https://i.imgur.com/apRGhAb.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (17, 'https://i.imgur.com/3Yf1zfo.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (17, 'https://i.imgur.com/wfGJuUs.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (17, 'https://i.imgur.com/QRvBiKz.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (17, 'https://i.imgur.com/X64HZie.jpeg');

# 18. 서래 스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (18, 'https://i.imgur.com/djKl94n.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (18, 'https://i.imgur.com/Gk90pQf.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (18, 'https://i.imgur.com/SeaAGha.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (18, 'https://i.imgur.com/eoxnEgO.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (18, 'https://i.imgur.com/g943zfm.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (18, 'https://i.imgur.com/YzNE3mN.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (18, 'https://i.imgur.com/Rdcd3Ct.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (18, 'https://i.imgur.com/gIpSAQy.jpeg');

# 19. 스튜디오 코스모스
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (19, 'https://i.imgur.com/vKjXDrQ.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (19, 'https://i.imgur.com/sahnsVb.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (19, 'https://i.imgur.com/IbMtTLo.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (19, 'https://i.imgur.com/rCKcbXh.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (19, 'https://i.imgur.com/hq05XPd.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (19, 'https://i.imgur.com/uIrOtBH.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (19, 'https://i.imgur.com/R31PVff.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (19, 'https://i.imgur.com/Cyq5Kmo.jpeg');

# 20. 옥토비스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (20, 'https://i.imgur.com/aWvuBLX.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (20, 'https://i.imgur.com/DcqPy3y.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (20, 'https://i.imgur.com/GxoSn46.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (20, 'https://i.imgur.com/d9qQyRx.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (20, 'https://i.imgur.com/K9sSI2G.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (20, 'https://i.imgur.com/iGXzRl2.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (20, 'https://i.imgur.com/0uXMrXC.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (20, 'https://i.imgur.com/UHRTEMr.jpeg');

# 21. 피노스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (21, 'https://i.imgur.com/eFxfGdW.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (21, 'https://i.imgur.com/reJDWrU.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (21, 'https://i.imgur.com/eC5dGoV.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (21, 'https://i.imgur.com/R03uozr.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (21, 'https://i.imgur.com/GAu4GE1.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (21, 'https://i.imgur.com/xpGgkMv.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (21, 'https://i.imgur.com/lnlADVu.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (21, 'https://i.imgur.com/6LjLaY3.jpeg');

# 22. 르브아스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (22, 'https://i.imgur.com/HXELK3N.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (22, 'https://i.imgur.com/9l6hdg7.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (22, 'https://i.imgur.com/g9Q0tnL.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (22, 'https://i.imgur.com/DULwkz9.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (22, 'https://i.imgur.com/TMHpbE0.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (22, 'https://i.imgur.com/upTgaOn.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (22, 'https://i.imgur.com/dUgBVQc.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (22, 'https://i.imgur.com/BaKvitP.jpeg');

# 23. 스튜디오아이엔씨
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (23, 'https://i.imgur.com/IUDAV6j.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (23, 'https://i.imgur.com/OfYQWoM.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (23, 'https://i.imgur.com/Uf49JBe.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (23, 'https://i.imgur.com/pPEzWVj.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (23, 'https://i.imgur.com/p8s3pte.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (23, 'https://i.imgur.com/kcs7Kka.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (23, 'https://i.imgur.com/ndHk8l9.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (23, 'https://i.imgur.com/kq99UDB.jpeg');

# 24. 파스타스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (24, 'https://i.imgur.com/WflrjBF.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (24, 'https://i.imgur.com/uhMRQh3.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (24, 'https://i.imgur.com/VlXpkRA.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (24, 'https://i.imgur.com/9haHU8L.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (24, 'https://i.imgur.com/HEB7cJm.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (24, 'https://i.imgur.com/TY84KNn.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (24, 'https://i.imgur.com/JDuqVA5.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (24, 'https://i.imgur.com/VXBMpcB.jpeg');

# 25. 뮬라이트스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (25, 'https://i.imgur.com/8qfIFeN.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (25, 'https://i.imgur.com/2MlkAPp.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (25, 'https://i.imgur.com/zkwoUDb.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (25, 'https://i.imgur.com/quUbWG1.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (25, 'https://i.imgur.com/78dktVT.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (25, 'https://i.imgur.com/unqNLpp.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (25, 'https://i.imgur.com/gh3CzkL.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (25, 'https://i.imgur.com/c9X3fuj.jpeg');

# 26. 스튜디오 알로
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (26, 'https://i.imgur.com/WJlVLgO.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (26, 'https://i.imgur.com/WX85Cqk.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (26, 'https://i.imgur.com/hDUEcJi.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (26, 'https://i.imgur.com/0ARB923.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (26, 'https://i.imgur.com/bztqQa5.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (26, 'https://i.imgur.com/bO3Tkod.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (26, 'https://i.imgur.com/c7gViXi.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (26, 'https://i.imgur.com/GUVLX7S.jpeg');

# 27. 시노그라피
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (27, 'https://i.imgur.com/YpYSJy9.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (27, 'https://i.imgur.com/3MRmvyO.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (27, 'https://i.imgur.com/k4sxjNb.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (27, 'https://i.imgur.com/sockXgk.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (27, 'https://i.imgur.com/RTg630t.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (27, 'https://i.imgur.com/FsXwfXK.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (27, 'https://i.imgur.com/YgfDR0n.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (27, 'https://i.imgur.com/z2rkS54.jpeg');

# 28. 최현민 스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (28, 'https://i.imgur.com/T41IjxG.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (28, 'https://i.imgur.com/zTwUyII.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (28, 'https://i.imgur.com/9kIRjs4.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (28, 'https://i.imgur.com/uHf9q4C.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (28, 'https://i.imgur.com/7Gqz5p1.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (28, 'https://i.imgur.com/ZX74cW5.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (28, 'https://i.imgur.com/GktaEMy.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (28, 'https://i.imgur.com/hgIy6X3.jpeg');

# 29. 라라트 스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (29, 'https://i.imgur.com/NZ2idHN.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (29, 'https://i.imgur.com/dGo1hyO.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (29, 'https://i.imgur.com/FQCRx4l.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (29, 'https://i.imgur.com/1XwFNzi.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (29, 'https://i.imgur.com/p6TMdEF.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (29, 'https://i.imgur.com/95vV91E.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (29, 'https://i.imgur.com/wB2bipE.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (29, 'https://i.imgur.com/vGASt4J.jpeg');

# 30. 프롬선스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (30, 'https://i.imgur.com/iBlc2AS.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (30, 'https://i.imgur.com/fNfISfk.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (30, 'https://i.imgur.com/PV4pOCX.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (30, 'https://i.imgur.com/7dIaEzN.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (30, 'https://i.imgur.com/Ctcosu7.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (30, 'https://i.imgur.com/GWEXCTG.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (30, 'https://i.imgur.com/jZrGBQd.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (30, 'https://i.imgur.com/Z9uX6Ei.jpeg');

# 31. FF 스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (31, 'https://i.imgur.com/bg7uRMT.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (31, 'https://i.imgur.com/54t9W3s.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (31, 'https://i.imgur.com/CMubpDm.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (31, 'https://i.imgur.com/OjMAxsg.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (31, 'https://i.imgur.com/7AvswKb.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (31, 'https://i.imgur.com/xkb5ltX.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (31, 'https://i.imgur.com/6FObEBS.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (31, 'https://i.imgur.com/v8NK4Zt.jpeg');

# 32. 스튜디오피플
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, 'https://i.imgur.com/Wuz8Emn.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, 'https://i.imgur.com/HvJNh1n.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, 'https://i.imgur.com/V9PoM7L.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, 'https://i.imgur.com/EtDndmZ.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, 'https://i.imgur.com/FczO2gA.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, 'https://i.imgur.com/5GAkkUZ.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, 'https://i.imgur.com/6TuCXTT.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, 'https://i.imgur.com/nKnFNZM.jpeg');

# 33. 오해피데이스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (33, 'https://i.imgur.com/SpU9itK.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (33, 'https://i.imgur.com/iKzylHD.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (33, 'https://i.imgur.com/JyIwOdi.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (33, 'https://i.imgur.com/hjgJz0g.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (33, 'https://i.imgur.com/zHHCzqg.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (33, 'https://i.imgur.com/op1UmIY.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (33, 'https://i.imgur.com/ZBJ21C6.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (33, 'https://i.imgur.com/39QRwpX.jpeg');

# 34. 그믐달 홍대점
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (34, 'https://i.imgur.com/niY3nhv.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (34, 'https://i.imgur.com/a/gw20ien.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (34, 'https://i.imgur.com/a/sVyy92z.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (34, 'https://i.imgur.com/a/XdYOPzb.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (34, 'https://i.imgur.com/a/7Ni9ZdY.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (34, 'https://i.imgur.com/a/RaPrcLZ.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (34, 'https://i.imgur.com/a/fm7yTCR.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (34, 'https://i.imgur.com/a/6JFJUUm.jpeg');

# 35. 그믐달 청담점
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, 'https://i.imgur.com/niY3nhv.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, 'https://i.imgur.com/a/gw20ien.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, 'https://i.imgur.com/a/sVyy92z.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, 'https://i.imgur.com/a/XdYOPzb.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, 'https://i.imgur.com/a/7Ni9ZdY.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, 'https://i.imgur.com/a/RaPrcLZ.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, 'https://i.imgur.com/a/fm7yTCR.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, 'https://i.imgur.com/a/6JFJUUm.jpeg');

# 36. 버터플라이하우스
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (36, 'https://i.imgur.com/a/30H8uiC.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (36, 'https://i.imgur.com/a/rcT8CSa.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (36, 'https://i.imgur.com/a/39JU9fu.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (36, 'https://i.imgur.com/a/NKLOJRA.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (36, 'https://i.imgur.com/a/QAjzKuI.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (36, 'https://i.imgur.com/a/kZHU0S0.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (36, 'https://i.imgur.com/a/eRjJrCm.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (36, 'https://i.imgur.com/a/L96tRar.jpeg');

# 37. 스쿠피스튜디오
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (37, 'https://i.imgur.com/a/7KTBrlP.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (37, 'https://i.imgur.com/a/mPtFCgV.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (37, 'https://i.imgur.com/a/lS8Rd1Y.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (37, 'https://i.imgur.com/a/NsfGEnw.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (37, 'https://i.imgur.com/a/cO9ypHp.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (37, 'https://i.imgur.com/a/Z7tT14H.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (37, 'https://i.imgur.com/a/opPqhMg.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (37, 'https://i.imgur.com/a/vXyDmAN.jpeg');

# 38. 류앤라이크
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (38, 'https://i.imgur.com/a/gh70Vx5.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (38, 'https://i.imgur.com/a/8JwdrKo.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (38, 'https://i.imgur.com/a/kL61agJ.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (38, 'https://i.imgur.com/a/LJNQJjq.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (38, 'https://i.imgur.com/a/sXZbvPp.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (38, 'https://i.imgur.com/a/lmkdWZy.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (38, 'https://i.imgur.com/a/FVLa4Fb.jpeg');
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (38, 'https://i.imgur.com/a/poYcxuE.jpeg');

INSERT INTO profile (studio_id, profileURL) VALUES
                                                (1, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (2, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (3, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (4, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (5, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (6, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (7, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (8, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (9, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (10, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (11, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (12, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (13, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (14, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (15, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (16, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (17, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (18, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (19, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (20, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (21, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (22, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (23, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (24, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (25, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (26, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (27, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (28, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (29, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (30, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (31, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (32, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (33, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (34, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (35, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (36, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (37, 'https://i.imgur.com/Uw5nNHQ.png'),
                                                (38, 'https://i.imgur.com/Uw5nNHQ.png');