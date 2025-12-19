package nextstep.courses.enrollment.service;

import nextstep.courses.cohort.service.repository.CohortRepository;
import nextstep.courses.course.domain.Course;
import nextstep.courses.course.service.repository.CourseRepository;
import nextstep.courses.enrollment.service.dto.EnrollmentSaveRequest;
import nextstep.qna.exception.unchecked.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {
    private final CourseRepository courseRepository;
    private final CohortRepository cohortRepository;

    @Autowired
    public EnrollmentService(CourseRepository courseRepository, CohortRepository cohortRepository) {
        this.courseRepository = courseRepository;
        this.cohortRepository = cohortRepository;
    }

    @Transactional
    public void saveEnrollment(EnrollmentSaveRequest request) {
        // req 기반 결제정보 확인
        // 결제모듈에 동기 이벤트 발송해서 확인필요

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(NotFoundException::new);

        // course에 수강가능한지 질문

        // Course에 cohorts 넣어놓고 조회시에 한번에 땡겨와서 처리해야함.
        // course 유/무료상태 확인후 유료인 경우 결제확인 과정 처리
        // cohort 조회 및 모집중 상태확인
        // cohort 수강신청 인원 남았는지 확인
        //
        // enrollment 객체 리턴

        // enrollment 저장.

    }
}
