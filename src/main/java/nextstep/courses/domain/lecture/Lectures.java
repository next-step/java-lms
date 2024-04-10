package nextstep.courses.domain.lecture;

import java.util.Set;

public class Lectures {

    private final Set<Lecture> lectures;

    public Lectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
    }
//
//    public Lecture findLecture() {
//        //코드 작성해줘
//    }
}
