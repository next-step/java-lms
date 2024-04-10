package nextstep.courses.domain.lecture;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import nextstep.courses.error.exception.NotExistLecture;

public class Lectures {

    private final Map<LectureName, Lecture> lectures = new HashMap<>();

    public void addLecture(LectureName lectureName, Lecture lecture) {
        lectures.put(lectureName, lecture);
    }

    public Lecture deleteLecture(LectureName lectureName) {
        return lectures.remove(lectureName);
    }

    public Lecture findLecture(LectureName lectureName) {
        return Optional.ofNullable(lectures.getOrDefault(lectureName, null))
            .orElseThrow(() -> new NotExistLecture(lectureName.getValue()));
    }
}
