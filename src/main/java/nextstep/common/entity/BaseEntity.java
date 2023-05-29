package nextstep.common.entity;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;

public class BaseEntity {

    protected Long id;

    protected boolean deleted = false;

    protected LocalDateTime createdDate = now();

    protected LocalDateTime updatedDate;

}
