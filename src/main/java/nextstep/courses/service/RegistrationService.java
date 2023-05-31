package nextstep.courses.service;

import nextstep.courses.CannotRegisterException;
import nextstep.courses.domain.Registration;
import org.springframework.stereotype.Service;

@Service("registrationService")
public class RegistrationService {

    public void save(Registration registration) throws CannotRegisterException {
        validate(registration);
        //todo.
    }

    private void validate(Registration registration) throws CannotRegisterException {
//        final Session session = sessionRepository.findById(registration.getSessionId());
//        final List<Registration> registrations = registrationRepository.findBySessionId(registration.getSessionId());

//        if (!session.canRegisteringStatus()) {
//            throw new CannotRegisterException("모집중인 상태가 아닙니다.");
//        }
//
//        if (session.overMaxStudents(registrations.size())) {
//            throw new CannotRegisterException("최대 수강 인원을 초과할 수 없습니다.");
//        }
    }
}
