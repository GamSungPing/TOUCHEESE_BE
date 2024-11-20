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

INSERT INTO portfolio (studio_id, portfolioURL) VALUES (1, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (1, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (2, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (2, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (3, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (3, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (4, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (4, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (5, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (5, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (6, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (6, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (6, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (7, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (7, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (7, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (8, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (8, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (9, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (9, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (10, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (10, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (11, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (11, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (12, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (12, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (13, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (13, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (14, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (14, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (15, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (15, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (16, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (16, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (17, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (17, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (18, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (18, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (19, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (19, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (20, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (20, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (21, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (21, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (22, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (22, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (23, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (24, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (24, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (24, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (25, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (25, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (26, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (26, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (27, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (28, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (28, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (29, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (30, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (30, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (30, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (31, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (31, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (32, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (33, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (33, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (34, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (35, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (36, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (37, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (37, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (38, "https://i.imgur.com/Uw5nNHQ.png");
INSERT INTO portfolio (studio_id, portfolioURL) VALUES (38, "https://i.imgur.com/Uw5nNHQ.png");

INSERT INTO profile (studio_id, profileURL) VALUES
                                                     (1, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (2, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (3, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (4, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (5, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (6, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (7, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (8, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (9, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (10, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (11, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (12, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (13, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (14, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (15, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (16, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (17, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (18, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (19, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (20, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (21, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (22, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (23, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (24, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (25, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (26, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (27, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (28, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (29, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (30, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (31, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (32, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (33, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (34, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (35, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (36, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (37, "https://i.imgur.com/Uw5nNHQ.png"),
                                                     (38, "https://i.imgur.com/Uw5nNHQ.png");
