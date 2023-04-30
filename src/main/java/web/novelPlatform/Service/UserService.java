package web.novelPlatform.Service;

import web.novelPlatform.Entity.User;
import web.novelPlatform.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //final된 것을 autowired(필드 주입)를 쓰지 않아도 주입 ->
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user){
        validateDuplicateUser(user);
        userRepository.save(user);

        return user.getId();
    }

    private void validateDuplicateUser(User user){
        List<User> findUsers = userRepository.findByName(user.getName());

        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 필명입니다.");
        }
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }


}
