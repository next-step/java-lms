package nextstep.lms.domain;

import nextstep.lms.UnAuthorizedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Session {
    private static final AtomicLong idGenerator = new AtomicLong(1);
    private Long id;

    private String title;

    private Course course;

    private LmsUser creator;

    private Integer price;  // 0 : 무료강의

    private SessionStatus status;

    private Integer maxApplicantCount;

    private List<LmsUser> applicants;

    private SessionCoverImg coverImg;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Session(Long id, String title, Course course, LmsUser creator, Integer price, SessionStatus status, Integer maxApplicantCount, List<LmsUser> applicants, SessionCoverImg coverImg, LocalDate startDate, LocalDate endDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.course = course;
        this.creator = creator;
        this.price = price;
        this.status = status;
        this.maxApplicantCount = maxApplicantCount;
        this.applicants = applicants;
        this.coverImg = coverImg;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private Session() {
    }

    private static void valiateCreatorAuthorization(Course course, LmsUser creator) {
        if(!creator.isAdmin()){
            throw new UnAuthorizedException("강의 생성 권한이 없는 유저입니다.");
        }
        if(!course.isSameCreator(creator)){
            throw new UnAuthorizedException("강의 생성자와 과정 생성자가 일치하지 않습니다.");
        }
    }

    public static Session create(String title, Course course, LmsUser creator, Integer price, Integer maxApplicantCount, SessionCoverImg coverImg, LocalDate startDate, LocalDate endDate) {
        valiateCreatorAuthorization(course, creator);
        return new Session(idGenerator.getAndIncrement(), title, course, creator, price, SessionStatus.PREPARING, maxApplicantCount, new ArrayList<>(), coverImg, startDate, endDate, LocalDateTime.now(), null);
    }

    private void validateCreator(LmsUser user) {
        if (user != creator) {
            throw new UnAuthorizedException("강의 생성자만 강의 상태 변경이 가능합니다.");
        }
    }

    public void open(LmsUser user) {
        validateCreator(user);
        status = SessionStatus.OPEN;
        updatedAt = LocalDateTime.now();
    }

    public void close(LmsUser user) {
        validateCreator(user);
        status = SessionStatus.CLOSED;
        updatedAt = LocalDateTime.now();
    }

    public void addApplicant(LmsUser user) {
        SessionStatus.throwExceptionIfNotOpen(status);
        checkAlreadyApplicant(user);

        applicants.add(user);

        if(applicants.size() == maxApplicantCount){
            status = SessionStatus.FULL;
        }
    }

    private void checkAlreadyApplicant(LmsUser user) {
        if(applicants.contains(user)){
            throw new IllegalStateException("이미 신청한 회원입니다.");
        }
    }

    public boolean hasUser(LmsUser user) {
        return applicants.contains(user);
    }

    public Course getCourse() {
        return course;
    }

    public LmsUser getCreator() {
        return creator;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getMaxApplicantCount() {
        return maxApplicantCount;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
