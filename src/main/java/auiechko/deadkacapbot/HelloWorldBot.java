package auiechko.deadkacapbot;

import auiechko.deadkacapbot.processors.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class HelloWorldBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;

    private Processor processor;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        processor.process(update);
    }

    @Autowired
    public void setProcessor(@Lazy Processor processor) {
        this.processor = processor;
    }
}
