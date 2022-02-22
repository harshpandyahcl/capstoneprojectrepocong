package com.hcl.ppmtool.servicesTests;

import com.hcl.ppmtool.domain.User;
import com.hcl.ppmtool.repositories.UserRepository;
import com.hcl.ppmtool.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {



    @MockBean
    private UserRepository userRepositoryTest;
    @Autowired
    UserService userServiceTest;
    //Mockito.mock(UserService.class);
    @Test
    public void findAllUserTest(){
        long userTestId = 1;
        User testUser = new User();
        testUser.setId(userTestId);
        testUser.setUsername("Test@test.com");
        testUser.setFullName("Test User");
        testUser.setPassword("password");

        when(userRepositoryTest.findAll()).thenReturn(Stream
                .of(testUser).collect(Collectors.toList()));

        Iterable<User> list=userServiceTest.findAll();

        for (User u:list) {
            System.out.println(u.getUsername());
        }

    }

    @Test
    public void saveUserTest(){


        long userTestId = 1;
        User testUser = new User();
        testUser.setId(userTestId);
        testUser.setUsername("Test@test.com");
        testUser.setFullName("Test User");
        testUser.setPassword("password");

        Mockito.when(userRepositoryTest.save(Mockito.any(User.class))).thenReturn(testUser);

        User u = userServiceTest.saveUser(testUser);

        assertEquals("Test@test.com", u.getUsername());

    }

}