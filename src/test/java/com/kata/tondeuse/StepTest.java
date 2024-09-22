package com.kata.tondeuse;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
class StepTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    void testStep() throws Exception {
        // Lancer le step spécifique
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");

        // Obtenir StepExecution
        StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();

        // Vérifier l'état du step
        assertThat(stepExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }
}
