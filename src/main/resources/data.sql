INSERT INTO Toucheese.studio (name) VALUES ('Studio 1');
INSERT INTO Toucheese.studio (name) VALUES ('Studio 2');
INSERT INTO Toucheese.studio (name) VALUES ('Studio 3');
INSERT INTO Toucheese.studio (name) VALUES ('Studio 4');
INSERT INTO Toucheese.studio (name) VALUES ('Studio 5');
INSERT INTO Toucheese.studio (name) VALUES ('Studio 6');
INSERT INTO Toucheese.studio (name) VALUES ('Studio 7');
INSERT INTO Toucheese.studio (name) VALUES ('Studio 8');
INSERT INTO Toucheese.concept (name) VALUES ('발랄');
INSERT INTO Toucheese.concept (name) VALUES ('상큼');
INSERT INTO Toucheese.concept (name) VALUES ('시크');
INSERT INTO Toucheese.concept (name) VALUES ('뽀짝');
INSERT INTO Toucheese.member (name) VALUES ('member1');
INSERT INTO Toucheese.member (name) VALUES ('member2');
INSERT INTO Toucheese.member (name) VALUES ('member3');
INSERT INTO Toucheese.member (name) VALUES ('member4');
INSERT INTO Toucheese.member (name) VALUES ('member5');
insert into Toucheese.studio_concept (concept_id, studio_id) value (1, 1);
insert into Toucheese.studio_concept (concept_id, studio_id) value (1, 2);
insert into Toucheese.studio_concept (concept_id, studio_id) value (2, 3);
insert into Toucheese.studio_concept (concept_id, studio_id) value (2, 4);
insert into Toucheese.studio_concept (concept_id, studio_id) value (3, 5);
insert into Toucheese.studio_concept (concept_id, studio_id) value (3, 6);
insert into Toucheese.studio_concept (concept_id, studio_id) value (4, 7);
insert into Toucheese.studio_concept (concept_id, studio_id) value (4, 8);
INSERT INTO Toucheese.rating (studio_id, member_id, rating, created_at) VALUES (2, 1, 5, NOW());
INSERT INTO Toucheese.rating (studio_id, member_id, rating, created_at) VALUES (2, 2, 4, NOW());
INSERT INTO Toucheese.rating (studio_id, member_id, rating, created_at) VALUES (2, 3, 4, NOW());
INSERT INTO Toucheese.rating (studio_id, member_id, rating, created_at) VALUES (1, 4, 3, NOW());
INSERT INTO Toucheese.rating (studio_id, member_id, rating, created_at) VALUES (1, 5, 3, NOW());