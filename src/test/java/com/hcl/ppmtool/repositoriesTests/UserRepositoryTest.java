package com.hcl.ppmtool.repositoriesTests;

import com.hcl.ppmtool.domain.User;
import com.hcl.ppmtool.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void initUseCase() {
        List<User> users = Arrays.asList(
                new User(1L,"harsh@gmail.com","Harsh Pandya","password")
        );
        userRepository.saveAll(users);
    }

    @AfterEach
    public void destroyAll(){
        userRepository.deleteAll();
    }


    @Test
    void saveAllTest() {
        List<User> users = Arrays.asList(
                new User(1L,"harsh@gmail.com","Harsh Pandya","password"),
                new User(2L,"james@gmail.com","James l","password1"),
                new User(3L,"cong@gmail.com","cong n","password2")
        );
        Iterable<User> allUser = userRepository.saveAll(users);

        AtomicInteger validIdFound = new AtomicInteger();
        allUser.forEach(user -> {
            if(user.getId()>0){
                validIdFound.getAndIncrement();
            }
        });

        assertThat(validIdFound.intValue()).isEqualTo(3);
    }

    @Test
    void findAllTest() {
        List<User> allCustomer = (List<User>) userRepository.findAll();
        assertThat(allCustomer.size()).isGreaterThanOrEqualTo(1);
    }
}

