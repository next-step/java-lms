package nextstep.users.service;

import exception.LmsException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.exception.UserExceptionCode;
import org.springframework.stereotype.Service;

@Service
public class NsUserService {

  private final UserRepository jdbcUserRepository;

  public NsUserService(UserRepository jdbcUserRepository) {
    this.jdbcUserRepository = jdbcUserRepository;
  }

  public NsUser getUser (Long userId) {
    return jdbcUserRepository.findById(userId)
        .orElseThrow(() -> new LmsException(UserExceptionCode.USER_NOT_FOUND));
  }
}
