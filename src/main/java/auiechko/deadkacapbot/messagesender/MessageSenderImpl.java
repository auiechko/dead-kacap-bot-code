package auiechko.deadkacapbot.messagesender;

import auiechko.deadkacapbot.HelloWorldBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MessageSenderImpl implements MessageSender {
    private HelloWorldBot helloWorldBot;

    @Override
    public void sendMessage(SendMessage sendMessage) {
        try {
            helloWorldBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setHelloWorldBot(HelloWorldBot helloWorldBot) {
        this.helloWorldBot = helloWorldBot;
    }
}
