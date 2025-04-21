package nextstep.courses.factory;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.session.SessionEntityImageMap;
import nextstep.courses.entity.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CourseFactory {

    private final SessionFactory sessionFactory;

    @Autowired
    public CourseFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Course create(CourseEntity courseEntity, SessionEntityImageMap sessionEntityImageMap) throws IOException {
        return new Course(
            courseEntity.getId(),
            courseEntity.isDeleted(),
            courseEntity.getTitle(),
            courseEntity.getCreatorId(),
            sessionFactory.createSessions(sessionEntityImageMap),
            courseEntity.getCreatedAt(),
            courseEntity.getUpdatedAt()
        );
    }
}
