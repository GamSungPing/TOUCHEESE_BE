package com.rocket.toucheese_be.domain.studio.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudioConcept is a Querydsl query type for StudioConcept
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudioConcept extends EntityPathBase<StudioConcept> {

    private static final long serialVersionUID = -957190619L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudioConcept studioConcept = new QStudioConcept("studioConcept");

    public final QConcept concept;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStudio studio;

    public QStudioConcept(String variable) {
        this(StudioConcept.class, forVariable(variable), INITS);
    }

    public QStudioConcept(Path<? extends StudioConcept> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudioConcept(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudioConcept(PathMetadata metadata, PathInits inits) {
        this(StudioConcept.class, metadata, inits);
    }

    public QStudioConcept(Class<? extends StudioConcept> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.concept = inits.isInitialized("concept") ? new QConcept(forProperty("concept")) : null;
        this.studio = inits.isInitialized("studio") ? new QStudio(forProperty("studio"), inits.get("studio")) : null;
    }

}

