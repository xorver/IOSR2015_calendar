package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.agh.student.dto.UserDto;
import pl.edu.agh.student.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "user")
    @ResponseBody
    public UserDto get(HttpServletRequest request) {
        return userService.getUserDtoByHttpServletRequest(request);
    }

}