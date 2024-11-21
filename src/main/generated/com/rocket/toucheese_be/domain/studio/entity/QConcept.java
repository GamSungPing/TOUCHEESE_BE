package com.rocket.toucheese_be.domain.studio.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConcept is a Querydsl query type for Concept
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConcept extends EntityPathBase<Concept> {

    private static final long serialVersionUID = -1842303205L;

    public static final QConcept concept = new QConcept("concept");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<StudioConcept, QStudioConcept> studioConceptList = this.<StudioConcept, QStudioConcept>createList("studioConceptList", StudioConcept.class, QStudioConcept.class, PathInits.DIRECT2);

    public QConcept(String variable) {
        super(Concept.class, forVariable(variable));
    }

    public QConcept(Path<? extends Concept> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConcept(PathMetadata metadata) {
        super(Concept.class, metadata);
    }

}

