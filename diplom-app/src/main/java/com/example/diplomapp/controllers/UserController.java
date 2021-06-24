package com.example.diplomapp.controllers;

import com.example.diplomapp.entities.User;
import com.example.diplomapp.repos.ProjectRepository;
import com.example.diplomapp.repos.UserRepository;
import com.example.diplomapp.services.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    private final UserService userService;

    public UserController(UserRepository userRepository, ProjectRepository projectRepository, UserService userService) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    @GetMapping("workerList")
    public String workerList(Model model){
        model.addAttribute("workers", userRepository.findAll());
        return "workerList";
    }

    @GetMapping("projectList")
    public String projectList(Model model){
        model.addAttribute("projects", projectRepository.findAll());
        return "projectList";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {

        model.addAttribute("password", user.getPassword());
        model.addAttribute("name", user.getName());
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("patronymic", user.getPatronymic());
        model.addAttribute("salary", user.getSalary());
        model.addAttribute("department", user.getDepartment());
        model.addAttribute("qualification", user.getQualification());
        model.addAttribute("position", user.getPosition());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("filename", user.getFilename());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
        @AuthenticationPrincipal User user,
        @RequestParam("file") MultipartFile file,
        @RequestParam("position") String position,
        @RequestParam("department") String department,
        @RequestParam("qualification") String qualification,
        @RequestParam("salary") String salary
    ) throws IOException {
        String filename = "";

        if (ObjectUtils.isNotEmpty(file.getOriginalFilename())) {
            File uploadDirectory = new File(uploadPath);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            filename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + filename));
        }

        userService.updateProfile(user, filename, position, department, qualification, salary);

        return "redirect:/user/profile";
    }

    @PreAuthorize("hasAuthority('CHIEF')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("password", user.getPassword());
        model.addAttribute("name", user.getName());
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("patronymic", user.getPatronymic());
        model.addAttribute("salary", user.getSalary());
        model.addAttribute("department", user.getDepartment());
        model.addAttribute("qualification", user.getQualification());
        model.addAttribute("position", user.getPosition());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("filename", user.getFilename());
        model.addAttribute("phone", user.getPhone());
        return "profile";
    }
}
