package ru.lilitweb.testsystem.shell;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lilitweb.testsystem.models.TestResultModel;
import ru.lilitweb.testsystem.service.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class TestCommandsTest {

    @Autowired
    private Shell shell;

    @MockBean
    TestRunnerService testRunnerService;

    @MockBean
    LocalisationService localisationService;

    @MockBean
    FileResolverService fileResolverService;


    @Test
    void start() {
        String fio = "fio";
        when(localisationService.getMessage("test.commands.start.success")).thenReturn("success");
        assertThat(shell.evaluate(() -> "start --fio " + fio)).isEqualTo("success");
        ;
    }

    @Test
    void next() throws TestQuestionsFinishedException {
        when(testRunnerService.getNextQuestion()).thenReturn("question");
        when(testRunnerService.canAskNextQuestion()).thenReturn(true);
        assertThat(shell.evaluate(() -> "next")).isEqualTo("question");
        ;
    }

    @Test
    void answer() {
        when(testRunnerService.isFinishedAllQuestionsAnswering()).thenReturn(false);
        when(testRunnerService.isWaitAnswer()).thenReturn(true);
        when(localisationService.getMessage("test.commands.answer.success")).thenReturn("success");

        String answer = "answer";
        assertThat(shell.evaluate(() -> "answer " + answer)).isEqualTo("success");
        ;
    }

    @Test
    void answerIfEndTest() {
        when(testRunnerService.isFinishedAllQuestionsAnswering()).thenReturn(true);
        when(testRunnerService.isWaitAnswer()).thenReturn(true);
        when(localisationService.getMessage("test.commands.testfinished")).thenReturn("finished");

        String answer = "answer";
        assertThat(shell.evaluate(() -> "answer " + answer)).isEqualTo("finished");
        ;
    }

    @Test
    void stop() {
        when(testRunnerService.isStarted()).thenReturn(true);
        when(localisationService.getMessage("test.commands.exit.success")).thenReturn("finished");

        String answer = "answer";
        assertThat(shell.evaluate(() -> "stop")).isEqualTo("finished");
        ;
    }

    @Test
    void print() {
        TestResultModel testResultModel = mock(TestResultModel.class);

        when(testRunnerService.isFinishedAllQuestionsAnswering()).thenReturn(true);
        when(testRunnerService.getResult()).thenReturn(testResultModel);
        when(testResultModel.isPassed()).thenReturn(true);

        shell.evaluate(() -> "print");

        verify(localisationService, atLeastOnce()).getMessage("test.result.caption.results");
        verify(localisationService, atLeastOnce()).getMessage("test.result.caption.ispassed");
        verify(localisationService, atLeastOnce()).getMessage("test.result.success");
    }

    @Test
    void printFail() {
        TestResultModel testResultModel = mock(TestResultModel.class);
        when(testRunnerService.isFinishedAllQuestionsAnswering()).thenReturn(true);
        when(testRunnerService.getResult()).thenReturn(testResultModel);
        when(testResultModel.isPassed()).thenReturn(false);

        shell.evaluate(() -> "print");

        verify(localisationService, atLeastOnce()).getMessage("test.result.failed");
    }

    @Test
    void startAvailabilityIfNotAllowed() {
        when(testRunnerService.isStarted()).thenReturn(true);
        when(localisationService.getMessage("test.commands.availability.start")).thenReturn("not allowed");
        assertThat(shell.evaluate(() -> "start --fio somefio").toString().contains("not allowed"));
    }

    @Test
    void nextAvailabilityIfNotAllowed() {
        when(testRunnerService.canAskNextQuestion()).thenReturn(false);
        when(localisationService.getMessage("test.commands.availability.unavailable")).thenReturn("not allowed");
        assertThat(shell.evaluate(() -> "next").toString().contains("not allowed"));
    }

    @Test
    void answerAvailabilityIfNotAllowed() {
        when(testRunnerService.isWaitAnswer()).thenReturn(false);
        when(localisationService.getMessage("test.commands.availability.unavailable")).thenReturn("not allowed");
        assertThat(shell.evaluate(() -> "answer someanwer").toString().contains("not allowed"));
    }

    @Test
    void stopAvailabilityIfNotAllowed() {
        when(testRunnerService.isStarted()).thenReturn(false);
        when(localisationService.getMessage("test.commands.availability.exit")).thenReturn("not allowed");
        assertThat(shell.evaluate(() -> "stop").toString().contains("not allowed"));
    }

    @Test
    void printAvailabilityIfNotAllowed() {
        when(testRunnerService.isFinishedAllQuestionsAnswering()).thenReturn(false);
        when(localisationService.getMessage("test.commands.availability.unavailable")).thenReturn("not allowed");
        assertThat(shell.evaluate(() -> "print").toString().contains("not allowed"));
    }
}
