package com.example.diplomapp.services;

import com.example.diplomapp.entities.User;
import com.example.diplomapp.repos.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) { this.userRepo = userRepo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepo.findByUsername(username);
        if(ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (!ObjectUtils.isEmpty(userFromDb)) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    public void updateProfile(User user, String filename, String position, String department, String qualification, String salary) {
        if (!StringUtils.isEmpty(filename)) {
            user.setFilename(filename);
        }

        if (StringUtils.isNotEmpty(position)) {
            user.setPosition(position);
        }

        if (StringUtils.isNotEmpty(department)) {
            user.setDepartment(department);
        }

        if (StringUtils.isNotEmpty(qualification)) {
            user.setQualification(qualification);
        }

        if (StringUtils.isNotEmpty(salary)) {
            user.setSalary(Double.parseDouble(salary));
        }

        userRepo.save(user);
    }
}
