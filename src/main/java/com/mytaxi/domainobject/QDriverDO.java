package com.mytaxi.domainobject;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDriverDO is a Querydsl query type for DriverDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDriverDO extends EntityPathBase<DriverDO> {

    private static final long serialVersionUID = 643340167L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDriverDO driverDO = new QDriverDO("driverDO");

    public final QCarDO carDO;

    public final DateTimePath<java.time.ZonedDateTime> dateCoordinateUpdated = createDateTime("dateCoordinateUpdated", java.time.ZonedDateTime.class);

    public final DateTimePath<java.time.ZonedDateTime> dateCreated = createDateTime("dateCreated", java.time.ZonedDateTime.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.mytaxi.domainvalue.OnlineStatus> onlineStatus = createEnum("onlineStatus", com.mytaxi.domainvalue.OnlineStatus.class);

    public final StringPath password = createString("password");

    public final StringPath username = createString("username");

    public QDriverDO(String variable) {
        this(DriverDO.class, forVariable(variable), INITS);
    }

    public QDriverDO(Path<? extends DriverDO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDriverDO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDriverDO(PathMetadata metadata, PathInits inits) {
        this(DriverDO.class, metadata, inits);
    }

    public QDriverDO(Class<? extends DriverDO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.carDO = inits.isInitialized("carDO") ? new QCarDO(forProperty("carDO")) : null;
    }

}

