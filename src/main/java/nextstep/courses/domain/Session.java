package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionStatus;
import nextstep.courses.enumeration.SessionType;

import java.time.LocalDateTime;

public class Session extends BaseEntity {

    private Long id;

    private String title;

    private SessionImages sessionImages;

    private SessionType sessionType;

    private Integer maxParticipants;

    private Price price;

    private SessionStatus status;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

}
