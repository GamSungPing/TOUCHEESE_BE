package com.rocket.toucheese_be.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1818209611L;

    public static final QMember member = new QMember("member1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final ListPath<com.rocket.toucheese_be.domain.studio.entity.Rating, com.rocket.toucheese_be.domain.studio.entity.QRating> rating = this.<com.rocket.toucheese_be.domain.studio.entity.Rating, com.rocket.toucheese_be.domain.studio.entity.QRating>createList("rating", com.rocket.toucheese_be.domain.studio.entity.Rating.class, com.rocket.toucheese_be.domain.studio.entity.QRating.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

