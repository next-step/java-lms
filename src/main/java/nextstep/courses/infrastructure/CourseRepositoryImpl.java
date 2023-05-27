package nextstep.courses.infrastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;

public class CourseRepositoryImpl implements CourseRepository {

    private static Map<Long, Course> courses = new HashMap<>();
    private static AtomicInteger courseId = new AtomicInteger(1);
    @Override
    public int save(Course course) {
        int id = courseId.getAndIncrement();
        courses.putIfAbsent((long) id, course);
        return id;
    }

    @Override
    public Course findById(Long id) {
        return courses.get(id);
    }
}
