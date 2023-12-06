package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Session {
    private Long id;
    private String title;
    private SessionDate sessionDate;
    private List<CoverImage> coverImages;
    private Enrollment enrollment;
    private Long creatorId;
    private LocalDateTime createdAt;
    private Long updatorId;
    private LocalDateTime updatedAt;

    public Session(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(title, price, new SessionDate(startDate, endDate), null);
    }

    public Session(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate, final CoverImage coverImage) {
        this(title, price, new SessionDate(startDate, endDate), coverImage);
    }

    public Session(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(title, 0L, new SessionDate(startDate, endDate), null);
    }

    public Session(final String title, final long price, final SessionDate sessionDate, final CoverImage coverImage) {
        this(0L, title, price, sessionDate, coverImage, Collections.emptyList(), 1L, LocalDateTime.now());
    }

    public Session(final long id, final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate, final CoverImage coverImage, final List<NsUser> nsUsers, final Long creatorId, final LocalDateTime createdAt) {
        this(id, title, price, new SessionDate(startDate, endDate), coverImage, nsUsers, creatorId, createdAt);
    }

    public Session(final long id, final String title, final long price, final SessionDate sessionDate, final CoverImage coverImage, final List<NsUser> nsUsers, final Long creatorId, final LocalDateTime createdAt) {
        validateSession(title, sessionDate);

        this.id = id;
        this.title = title;
        this.enrollment = new Enrollment(price, nsUsers);
        this.sessionDate = sessionDate;
        this.coverImages = validateCoverImage(coverImage);
        this.creatorId = creatorId;
        this.createdAt = createdAt;
    }

    private void validateSession(final String title, final SessionDate sessionDate) {
        Assert.hasText(title, "title cannot be blank");
        Assert.notNull(sessionDate, "session date cannot be null");
    }

    private List<CoverImage> validateCoverImage(CoverImage coverImage) {
        final List<CoverImage> list = new ArrayList<>();

        if (coverImage == null) {
            coverImage = CoverImage.defaultCoverImage();
        }

        list.add(coverImage);

        return list;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDateTime getStartDate() {
        return this.sessionDate.getStartDate();
    }

    public LocalDateTime getEndDate() {
        return this.sessionDate.getEndDate();
    }

    public int getMaxStudentLimit() {
        return this.enrollment.getMaxStudentLimit();
    }

    public void changeMaxStudentLimit(final int maxStudentLimit) {
        this.enrollment.changeMaxStudentLimit(maxStudentLimit);
    }

    public Long getId() {
        return this.id;
    }

    public List<CoverImage> getCoverImages() {
        return this.coverImages;
    }

    public void setCoverImages(final List<CoverImage> coverImages) {
        this.coverImages = coverImages;
    }

    public long getPrice() {
        return this.enrollment.getPrice();
    }

    public int getCurrentStudentCount() {
        return this.enrollment.getCurrentStudentCount();
    }

    private void setStatus(SessionStatus status) {
        this.enrollment.setStatus(status);
    }

    public boolean isNotRecruiting() {
        return !this.enrollment.isRecruiting();
    }

    public String getRecruitingStatusString() {
        return this.enrollment.getRecruitingStatusString();
    }

    public void ready() {
        setStatus(SessionStatus.READY);
    }

    public void recruit() {
        this.enrollment.setRecruitingStatus(RecruitingStatus.RECRUITING);
    }

    public void notRecruit() {
        this.enrollment.setRecruitingStatus(RecruitingStatus.NOT_RECRUITING);
    }

    public void onGoing() {
        setStatus(SessionStatus.ONGOING);
    }

    public void close() {
        setStatus(SessionStatus.CLOSED);
    }

    public void enroll(Payment payment, NsUser user) {
        enrollment.enroll(payment, user);
    }

    public String getSessionStatusString() {
        return this.enrollment.getSessionStatusString();
    }

    public void changeSessionStatus(final SessionStatus sessionStatus) {
        setStatus(sessionStatus);
    }

    public List<NsUser> getUsers() {
        return this.enrollment.getUsers();
    }
}
