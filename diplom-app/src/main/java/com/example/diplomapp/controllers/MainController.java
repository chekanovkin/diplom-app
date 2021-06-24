package com.example.diplomapp.controllers;

import com.example.diplomapp.entities.Project;
import com.example.diplomapp.entities.Role;
import com.example.diplomapp.entities.User;
import com.example.diplomapp.entities.WorkingDay;
import com.example.diplomapp.repos.ProjectRepository;
import com.example.diplomapp.repos.UserRepository;
import com.example.diplomapp.services.UserService;
import com.example.diplomapp.services.WorkingDayService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

@Controller
public class MainController {

    private final UserService userService;

    private final WorkingDayService workingDayService;

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    public MainController(UserService userService,
                         UserRepository userRepository,
                         ProjectRepository projectRepository,
                         WorkingDayService workingDayService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.workingDayService = workingDayService;
    }

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PreAuthorize("hasAuthority('CHIEF')")
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PreAuthorize("hasAuthority('CHIEF')")
    @PostMapping("/registration")
    public String addUser(
        @RequestParam("passwordConfirm") String passwordConfirm,
        @Valid User user,
        BindingResult bindingResult,
        Model model,
        @RequestParam(value = "isChief", required = false) String isChief
    ){
        boolean isEmpty = StringUtils.isEmpty(passwordConfirm);

        if(isEmpty) {
            model.addAttribute("password2Error", "Повторите пароль");
        }

        if(!StringUtils.isEmpty(user.getPassword()) && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Пароли не совпадают!");
        }
        if (isEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if(!userService.addUser(user)) {
            model.addAttribute("usernameError", "Пользователь уже существует");
            return "registration";
        }

        if(StringUtils.isEmpty(isChief)){
            user.setRoles(Collections.singleton(Role.WORKER));
        } else {
            user.setRoles(Collections.singleton(Role.CHIEF));
        }

        userRepository.save(user);
        return "redirect:/user/profile";
    }

    @GetMapping("/timeTracking")
    public String getTimeTracking() {
        return "timeTracking";
    }

    @GetMapping("/project/{id}")
    public String getProjectTasks(@PathVariable Project id, Model model) {
        model.addAttribute("project", id);
        return "projectTasks";
    }

    @PostMapping("/userSearch")
    public String getUserSearch(@RequestParam("searchRequest") String searchRequest, Model model) {
        model.addAttribute("workers", userRepository.findByName(searchRequest));
        return "workerList";
    }

    @PostMapping("/projectSearch")
    public String getProjectSearch(@RequestParam("searchRequest") String searchRequest, Model model) {
        model.addAttribute("projects", projectRepository.findByName(searchRequest));
        return "projectList";
    }

    @PostMapping("/checkIn")
    public String checkIn(@AuthenticationPrincipal User user) {
        LocalDate now = LocalDate.now();
        WorkingDay workedDay = new WorkingDay(now, user);
        workingDayService.addDayWorked(workedDay);
        return "timeTracking";
    }
}
