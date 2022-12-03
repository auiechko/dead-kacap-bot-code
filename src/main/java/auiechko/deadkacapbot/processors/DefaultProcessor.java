package auiechko.deadkacapbot.processors;

import auiechko.deadkacapbot.handlers.CallbackQueryHandler;
import auiechko.deadkacapbot.handlers.MessageHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class DefaultProcessor implements Processor {

    private final CallbackQueryHandler callbackQueryHandler;
    private final MessageHandler messageHandler;

    public DefaultProcessor(CallbackQueryHandler callbackQueryHandler, MessageHandler messageHandler) {
        this.callbackQueryHandler = callbackQueryHandler;
        this.messageHandler = messageHandler;
    }

    @Override
    public void executeMessage(Message message) {
        messageHandler.choose(message);
    }

    @Override
    public void executeCallBackQuerry(CallbackQuery callbackQuery) {
        callbackQueryHandler.choose(callbackQuery);
    }
}
