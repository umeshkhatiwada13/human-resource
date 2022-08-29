package com.humanresource.service.impl;

import com.humanresource.auth.User;
import com.humanresource.auth.UserRepo;
import com.humanresource.projection.UserProjection;
import com.humanresource.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author umeshkhatiwada13@infodev
 * @project spring-security
 * @created 27/08/2022 - 17:45
 */
@Service
@RequiredArgsConstructor
//@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(Integer id) throws Exception {
        Optional<User> userOpt = userRepo.findById(id);
        if (!userOpt.isPresent()) {
            throw new Exception("User not Present");
        }
        return userOpt.get();
    }

    @Override
    public void save(User user) {
        try {
            String finalPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(finalPassword);
            userRepo.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Integer id) {
        boolean success = false;
        try {
            Optional<User> userOpt = userRepo.findById(id);
            if (userOpt.isPresent()) {
                userRepo.updateDeletedStatusById(id);
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAllActive();
    }

    @Override
    public List<UserProjection> findAllUnassigned() {
        return userRepo.findAllUnassigned();
    }
}
