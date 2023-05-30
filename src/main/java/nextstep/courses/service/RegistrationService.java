package nextstep.courses.service;

import nextstep.courses.CannotRegisterException;
import nextstep.courses.domain.Registration;
import nextstep.courses.domain.RegistrationRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("registrationService")
public class RegistrationService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "registrationRepository")
    private RegistrationRepository registrationRepository;

    public void save(Registration registration) throws CannotRegisterException {
        validate(registration);
        //todo.
    }

    private void validate(Registration registration) throws CannotRegisterException {
        final Session session = sessionRepository.findById(registration.getSessionId());
        final List<Registration> registrations = registrationRepository.findBySessionId(registration.getSessionId());

        if (!session.canRegisteringStatus()) {
            throw new CannotRegisterException("모집중인 상태가 아닙니다.");
        }

        if (session.overMaxStudents(registrations.size())) {
            throw new CannotRegisterException("최대 수강 인원을 초과할 수 없습니다.");
        }
    }
}
