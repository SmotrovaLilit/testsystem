package ru.lilitweb.testsystem.shell.promtProviders;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;
import ru.lilitweb.testsystem.service.TestRunnerService;

@Component
public class TestPromptProvider implements PromptProvider {
    private final TestRunnerService service;

    @Autowired
    public TestPromptProvider(TestRunnerService service) {
        this.service = service;
    }

    @Override
    public AttributedString getPrompt() {
        if (service.isStarted()) {
            String str = String.format("%s (%d/%d):>", service.getPerson(),
                    service.getCurrentQuestionNumber(), service.getQuestionsCount());
            return new AttributedString(str,
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
        } else {
            return new AttributedString("shell:>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.BLACK));
        }
    }
}
