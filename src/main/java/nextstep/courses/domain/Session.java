package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {
    private Long id;

    private String title;

    private Period period;

    private String coverImageUrl;

    private Boolean isFree;

    private Registration registration;

    public Session(Long id, String title, Period period, String coverImageUrl, Boolean isFree, Registration registration) {
        this.id = id;
        this.title = title;
        this.period = period;
        this.coverImageUrl = coverImageUrl;
        this.isFree = isFree;
        this.registration = registration;
    }

    public void add(NsUser user) {
        registration.register(user);
    }
}
