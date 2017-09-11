package com.mytaxi.domainobject;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCarDO is a Querydsl query type for CarDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCarDO extends EntityPathBase<CarDO> {

    private static final long serialVersionUID = 1730368267L;

    public static final QCarDO carDO = new QCarDO("carDO");

    public final BooleanPath convertible = createBoolean("convertible");

    public final DateTimePath<java.time.ZonedDateTime> dateCreated = createDateTime("dateCreated", java.time.ZonedDateTime.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final EnumPath<com.mytaxi.domainvalue.EngineType> engineType = createEnum("engineType", com.mytaxi.domainvalue.EngineType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath licensePlate = createString("licensePlate");

    public final StringPath rating = createString("rating");

    public final StringPath seatCount = createString("seatCount");

    public QCarDO(String variable) {
        super(CarDO.class, forVariable(variable));
    }

    public QCarDO(Path<? extends CarDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCarDO(PathMetadata metadata) {
        super(CarDO.class, metadata);
    }

}

