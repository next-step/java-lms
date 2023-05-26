package nextstep.Fixtures;

import nextstep.courses.domain.CourseBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CourseFixtures {
    public static CourseBuilder testCourse1() {
        return CourseBuilder.init()
                .id(1L)
                .sessions(new ArrayList<>())
                .creatorId(1L)
                .createdAt(LocalDateTime.now());
    }
}
