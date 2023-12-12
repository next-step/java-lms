package nextstep.courses.domain.session;

import nextstep.courses.MaxImageSizeExceededException;
import nextstep.courses.MaxStudentsNumberExceededException;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class PaidSession extends Session {
    private static final String ERR_MAX_STUDENTS_EXCEEDED = String.format("해당 강의의 수강인원이 모두 차서 더 이상 등록할 수 없습니다.");

    private int maxStudentsNumber;

    public PaidSession(CoverImage image, LocalDate startDate, LocalDate endDate) {
        super(image, false, startDate, endDate);
    }

    public PaidSession(int maxStudentsNumber) {
        super(false);
        this.maxStudentsNumber = maxStudentsNumber;
    }

    public PaidSession(CoverImage image, LocalDate startDate, LocalDate endDate, int maxStudentsNumber) {
        this(image, startDate, endDate);
        this.maxStudentsNumber = maxStudentsNumber;
    }


    @Override
    public void register(NsUser user) throws Exception {
        validateMaxStudentsNumber();
        super.register(user);
    }

    private void validateMaxStudentsNumber() throws MaxStudentsNumberExceededException {
        if (this.students.size() >= this.maxStudentsNumber) {
            throw new MaxStudentsNumberExceededException(ERR_MAX_STUDENTS_EXCEEDED);
        }
    }
}
