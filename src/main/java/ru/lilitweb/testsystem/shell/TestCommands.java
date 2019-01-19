package ru.lilitweb.testsystem.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;
import ru.lilitweb.testsystem.models.TestResultModel;
import ru.lilitweb.testsystem.service.LocalisationService;
import ru.lilitweb.testsystem.service.TestQuestionsFinishedException;
import ru.lilitweb.testsystem.service.TestRunnerService;

@ShellComponent
@ShellCommandGroup("Test")
public class TestCommands {
    private final TestRunnerService testRunner;
    private final LocalisationService localisation;

    @Autowired
    public TestCommands(TestRunnerService testRunner, LocalisationService localisation) {
        this.testRunner = testRunner;
        this.localisation = localisation;
    }

    @ShellMethod("Start test")
    public String start(@ShellOption String fio) {
        testRunner.start(fio);
        return localisation.getMessage("test.commands.start.success");
    }

    @ShellMethod("Next question")
    public String next() throws TestQuestionsFinishedException {
        return testRunner.getNextQuestion();
    }

    @ShellMethod("Answer question")
    public String answer(String answer) {
        testRunner.answerQuestion(answer);
        if (testRunner.isFinishedAllQuestionsAnswering()) {
            return localisation.getMessage("test.commands.testfinished");
        }

        return localisation.getMessage("test.commands.answer.success");
    }

    @ShellMethod("Exit from test")
    public String stop() {
        testRunner.stop();
        return localisation.getMessage("test.commands.exit.success");
    }

    @ShellMethod("Print test result")
    public Table print() {
        TestResultModel report = testRunner.getResult();
        String[][] data = new String[2][2];

        //table title
        data[0][0] = localisation.getMessage("test.result.caption.results");
        data[0][1] = String.format("%d/%d", report.getSuccessAnswerCount(), report.getQuestionsCount());

        //table values
        data[1][0] = localisation.getMessage("test.result.caption.ispassed");
        data[1][1] = report.isPassed() ?
                localisation.getMessage("test.result.success") :
                localisation.getMessage("test.result.failed");

        TableModel table = new ArrayTableModel(data);
        return new TableBuilder(table)
                .addFullBorder(BorderStyle.fancy_light_double_dash)
                .build();
    }

    public Availability startAvailability() {
        return !testRunner.isStarted()
                ? Availability.available()
                : Availability.unavailable(localisation.getMessage("test.commands.availability.start"));
    }

    public Availability stopAvailability() {
        return testRunner.isStarted()
                ? Availability.available()
                : Availability.unavailable(localisation.getMessage("test.commands.availability.exit"));
    }

    public Availability answerAvailability() {
        if (testRunner.isWaitAnswer()) {
            return Availability.available();
        }

        return Availability.unavailable(localisation.getMessage("test.commands.availability.unavailable"));
    }

    public Availability nextAvailability() {
        if (testRunner.canAskNextQuestion()) {
            return Availability.available();
        }

        return Availability.unavailable(localisation.getMessage("test.commands.availability.unavailable"));
    }

    public Availability printAvailability() {
        return testRunner.isFinishedAllQuestionsAnswering()
                ? Availability.available()
                : Availability.unavailable(localisation.getMessage("test.commands.availability.unavailable"));
    }
}
