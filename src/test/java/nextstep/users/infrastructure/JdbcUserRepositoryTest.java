package nextstep.users.infrastructure;

import nextstep.fixture.TestFixture;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class JdbcUserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @DisplayName("findByUserId/code")
    @Test
    public void findByUserIdCode() {
        //given
        //when
        userRepository.findByUserId("");
        //then
        fail();
    }

    @DisplayName("save")
    @Test
    public void save() {
        //given
        NsUser user = TestFixture.BADAJIGI;
        //when
        NsUser save = userRepository.save(user);
        //then
        assertThat(save).as("").isNotNull();
        assertThat(save.getEmail()).as("").isEqualTo(user.getEmail());
        assertThat(save.getName()).as("").isEqualTo(user.getName());
        fail();
    }

    @DisplayName("findAll")
    @Test
    public void findAll() {
        //given
        NsUser user1 = TestFixture.BADAJIGI;
        NsUser user2 = TestFixture.JAVAJIGI;
        //when
        NsUser save1 = userRepository.save(user1);
        NsUser save2 = userRepository.save(user2);
        List<NsUser> all = userRepository.findAll();
        //then
        assertThat(all).as("").contains(save1);
        assertThat(all).as("").contains(save2);
        assertThat(all.size()).as("").isGreaterThan(2);
        fail();
    }
}