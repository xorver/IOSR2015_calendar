package pl.edu.agh.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Service;
import pl.edu.agh.student.db.model.User;
import pl.edu.agh.student.db.repository.UserRepository;
import pl.edu.agh.student.dto.UserDto;
import pl.edu.agh.student.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    public UserDto save(UserDto user) {
        return userMapper.toDto(userRepository.save(userMapper.fromDto(user)));
    }

    public List<UserDto> findAll() {
        return userMapper.toDto(userRepository.findAll());
    }

    public UserDto getUserByFacebookId(String id) {
        return userMapper.toDto(userRepository.findOne(id));
    }

    public UserDto getUserDtoByHttpServletRequest(HttpServletRequest request) {
        return userMapper.toDto(getUserByHttpServletRequest(request));
    }

    public List<UserDto> searchByNameOrLastname(String phrase) {
        checkNotNull(phrase);
        String[] phrases = phrase.trim().split("\\s+");
        StringBuilder regex = new StringBuilder();
        if (phrases.length > 0) {
            regex.append(phrases[0]);
            for (int i = 1; i < phrases.length; i++) {
                regex.append(phrases[0]).append("|");
            }
        }
        return userMapper.toDto(userRepository.findByNameOrLastNameRegex(regex.toString()));
    }

    public User getUserByHttpServletRequest(HttpServletRequest request) {
        if (request.getSession().getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SocialUser socialUser = (SocialUser) ((SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal();
            return userRepository.findOne(socialUser.getUserId());
        }
        return null;
    }
}