package auiechko.deadkacapbot.chatmembergetter;

import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;

public interface ChatMemberGetter {
    boolean GetChatMember(GetChatMember getChatMember);
}
