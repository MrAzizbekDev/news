package com.example.news.companent;

import com.example.news.entity.Role;
import com.example.news.entity.User;
import com.example.news.entity.enums.PermissionEnum;
import com.example.news.entity.enums.RoleConstants;
import com.example.news.repository.RoleRepository;
import com.example.news.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Arrays;

import static com.example.news.entity.enums.PermissionEnum.*;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String initMode;
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            PermissionEnum[] permissionEnums = PermissionEnum.values();

            Role admin = roleRepository.save(new Role(RoleConstants.ADMIN, Arrays.asList(permissionEnums),
                    "Owner system"));

            Role user = roleRepository.save(new Role(RoleConstants.USER, Arrays.asList(ADD_COMMENT, DELETE_MY_COMMENT
                    , EDIT_COMMENT),"Simple follower"));
            userRepository.save(new User("Admin", "admin",
                    passwordEncoder.encode("admin123"), admin, true));

            userRepository.save(new User("User", "user",
                    passwordEncoder.encode("user123"), user, true));

        }

    }
}
