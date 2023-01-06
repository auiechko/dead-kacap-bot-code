package auiechko.deadkacapbot.services;

import auiechko.deadkacapbot.messagesender.MessageSender;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class SendMessageService {

    private final MessageSender messageSender;

    public SendMessageService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void test1(Message message) {
        SendMessage msl = new SendMessage();
        msl.setChatId(message.getChatId());
        messageSender.sendMessage(msl);
    }
}
