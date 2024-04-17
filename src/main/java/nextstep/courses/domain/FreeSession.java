package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.List;

public class FreeSession extends Session {
    public FreeSession(Long id, String title, LocalDate startDate, LocalDate endDate, CoverImage coverImage, SessionStatus status) {
        super(id, title, startDate, endDate, coverImage, status);
    }

    public FreeSession(Long id, String title, LocalDate startDate, LocalDate endDate, CoverImage coverImage, SessionStatus status, List<NsUser> userList) {
        super(id, title, startDate, endDate, coverImage, status, userList);
    }

    @Override
    public void join(NsUser user) {
        if(this.status != SessionStatus.RECRUITING){
            throw new IllegalArgumentException("현재 강의 모집이 오픈하지 않았습니다");
        }

        if(hasUser(user)){
            throw new IllegalArgumentException("이미 수강중 입니다");
        }

        this.userList.add(user);
    }
}
