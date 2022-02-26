package com.hcl.ppmtool.repositoriesTests;

import com.hcl.ppmtool.domain.Project;
import com.hcl.ppmtool.domain.User;
import com.hcl.ppmtool.repositories.ProjectRepository;
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
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    
    @BeforeEach
    void initUseCase() {
        List<Project> projects = Arrays.asList(
                new Project(1L, "default project", "10111", "Project Description")
        );
        projectRepository.saveAll(projects);
    }

    @AfterEach
    public void destroyAll() {
        projectRepository.deleteAll();
    }


    @Test
    void findAllTest() {
        List<Project> allProject = (List<Project>) projectRepository.findAll();
        assertThat(allProject.size()).isGreaterThanOrEqualTo(1);
    }
}
