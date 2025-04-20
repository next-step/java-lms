package nextstep.courses.factory;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.session.SessionEntityImageMap;
import nextstep.courses.entity.CourseEntity;
import nextstep.courses.entity.SessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CourseFactory {

    private final SessionsFactory sessionsFactory;

    @Autowired
    public CourseFactory(SessionsFactory sessionsFactory) {
        this.sessionsFactory = sessionsFactory;
    }

    public Course create(CourseEntity courseEntity, SessionEntityImageMap sessionEntityImageMap) throws IOException {
        return new Course(
            courseEntity.getId(),
            courseEntity.isDeleted(),
            courseEntity.getTitle(),
            courseEntity.getCreatorId(),
            sessionsFactory.create(sessionEntityImageMap),
            courseEntity.getCreatedAt(),
            courseEntity.getUpdatedAt()
        );
    }
}
