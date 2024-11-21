package com.rocket.toucheese_be.domain.studio.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudio is a Querydsl query type for Studio
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudio extends EntityPathBase<Studio> {

    private static final long serialVersionUID = 819106499L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudio studio = new QStudio("studio");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<Portfolio, QPortfolio> portfolios = this.<Portfolio, QPortfolio>createList("portfolios", Portfolio.class, QPortfolio.class, PathInits.DIRECT2);

    public final StringPath priceCategory = createString("priceCategory");

    public final QProfile profileImage;

    public final NumberPath<Integer> profilePrice = createNumber("profilePrice", Integer.class);

    public final ListPath<Rating, QRating> ratingList = this.<Rating, QRating>createList("ratingList", Rating.class, QRating.class, PathInits.DIRECT2);

    public final QRegion region;

    public final ListPath<StudioConcept, QStudioConcept> studioConceptList = this.<StudioConcept, QStudioConcept>createList("studioConceptList", StudioConcept.class, QStudioConcept.class, PathInits.DIRECT2);

    public QStudio(String variable) {
        this(Studio.class, forVariable(variable), INITS);
    }

    public QStudio(Path<? extends Studio> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudio(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudio(PathMetadata metadata, PathInits inits) {
        this(Studio.class, metadata, inits);
    }

    public QStudio(Class<? extends Studio> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profileImage = inits.isInitialized("profileImage") ? new QProfile(forProperty("profileImage"), inits.get("profileImage")) : null;
        this.region = inits.isInitialized("region") ? new QRegion(forProperty("region")) : null;
    }

}

