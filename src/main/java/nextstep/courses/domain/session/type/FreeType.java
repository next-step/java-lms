package nextstep.courses.domain.session.type;

import nextstep.courses.domain.registration.Registrations;

public class FreeType implements SessionType {
    private final Registrations registrations;

    public FreeType() {
        this(new Registrations());
    }

    public FreeType(Registrations registrations) {
        this.registrations = registrations;
    }

    @Override
    public void validateEnroll(long payAmount) {
        // 무료 강의는 수강료 검증 없음, 정원 제한 없음
    }

    @Override
    public Registrations getRegistrations() {
        return registrations;
    }
}