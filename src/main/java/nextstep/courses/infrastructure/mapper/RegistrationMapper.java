package nextstep.courses.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.Registrations;
import nextstep.courses.infrastructure.entity.RegistrationEntity;

public class RegistrationMapper {

    private RegistrationMapper() {
    }

    public static RegistrationEntity toEntity(Registration registration) {
        return new RegistrationEntity(
            registration.getId(),
            registration.getSessionId(),
            registration.getStudentId(),
            registration.getEnrolledAt()
        );
    }

    public static Registration toDomain(RegistrationEntity entity) {
        return new Registration(
            entity.getId(),
            entity.getSessionId(),
            entity.getStudentId(),
            entity.getEnrolledAt()
        );
    }

}
