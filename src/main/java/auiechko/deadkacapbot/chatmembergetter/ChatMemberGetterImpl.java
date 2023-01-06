package auiechko.deadkacapbot.chatmembergetter;

import auiechko.deadkacapbot.HelloWorldBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;

@Service
public class ChatMemberGetterImpl implements ChatMemberGetter {
    private HelloWorldBot helloWorldBot;

    @Override
    public boolean GetChatMember(GetChatMember getChatMember) {
        try {
            ChatMember chatMember = helloWorldBot.execute(getChatMember);
            String status = chatMember.getStatus();
            if (status.equals("left") || status.equals("kicked") || status.equals("banned")) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Autowired
    public void setHelloWorldBot(HelloWorldBot helloWorldBot) {
        this.helloWorldBot = helloWorldBot;
    }
}
