package bitc.fullstack.sleepon.service;

import bitc.fullstack.sleepon.model.SleepOnUser;
import bitc.fullstack.sleepon.repository.SleepOnUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SleepOnUserService {

    @Autowired
    private SleepOnUserRepository userRepository;

    public void registerUser(SleepOnUser user) throws Exception {
        if (user.isUnderage()) {
            throw new Exception("2005년 이후 출생자는 가입할 수 없습니다.");
        }

        if (user.isManager()) {
            user.setManager("Y");
        }

        userRepository.save(user);
    }
}