package auiechko.deadkacapbot.handlers;

import auiechko.deadkacapbot.messagesender.MessageSender;
import auiechko.deadkacapbot.database.Database;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MessageHandler implements Handler<Message>{

    private final MessageSender messageSender;

    public MessageHandler(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void choose(Message message) {
        if(message.hasText()) {
            boolean user = Database.findUser(message);
            if (!user) {
                Database.addUser(message);
            }
            String text = message.getText();
            SendMessage msl = new SendMessage();
            msl.setChatId(message.getChatId());
            if(text.equals("/shot") || text.equals("/shot@dead_kacap_counter_bot")) {
                if (message.getDate() - Database.getLastUse(message) >= 120) {
                    long a = (long) (Math.random() * 91) + 10;
                    String word = "кацапів";
                    if (a % 10 == 1 && a != 11) { word = "кацапа"; }
                    long newKills = Database.getKills(message) + a;
                    Database.setKills(message, newKills);
                    Database.setLastUse(message, (long) message.getDate());
                    long newShots = Database.getShots(message) + 1;
                    Database.setShots(message, newShots);
                    msl.setText("Постріл! \nБавовни багато завдяки " + Database.getNickname(message) + ". \nТи йобнув " + a + " " + word + "!");
                } else {
                    long time = Database.getLastUse(message) + 120 - message.getDate();
                    long minutes = time / 60;
                    long seconds = time % 60;
                    msl.setText("Відпочинь, треба перезарядити хаймарс. \nСтріляй через " + minutes + "хв. " + seconds + "c.");
                }
            } else if (text.equals("/myrank")  || text.equals("/myrank@dead_kacap_counter_bot") || text.equals("/profile") || text.equals("/profile@dead_kacap_counter_bot")) {
                String word = "кацапів";
                long kills = Database.getKills(message);
                long shots = Database.getShots(message);
                if (kills % 10 == 1 && kills != 11) { word = "кацапа"; }
                if (shots == 0) {
                    shots++;
                }
                msl.setText(Database.getNickname(message) + ". Ти відправив " + kills + " " + word + " на той світ. \nКількість пострілів: " + shots + ". Середня кількість мертвих кацапів за твій постріл: " + kills / shots + ".");
            } else if (text.equals("/start")) {
                msl.setText("Готовий до бою! \n \nВся твоя суть це запускати мене, а я підрахую скільки кацапів попали на концерт кобзона без черги.");
            } else if (text.equals("/top") || text.equals("/top@dead_kacap_counter_bot") ) {
                String[] top = Database.getTop(message);
                String messageText = "Топ користувачів бота: \n \n";
                for (int i = 0; i < 10; i++) {
                    String lnTxt = "";
                    lnTxt += i + 1;
                    lnTxt += ". " + Database.getNicknameByID(top[i]) + " — " + Database.getKillsByID(top[i]) + "\n";
                    messageText += lnTxt;
                }
                msl.setText(messageText);
            } else if (text.equals("/topchat") || text.equals("/topchat@dead_kacap_counter_bot")) {
                if (message.getChat().isUserChat()) {
                    String word = "кацапів";
                    long kills = Database.getKills(message);
                    if (kills % 10 == 1 && kills != 11) { word = "кацапа"; }
                    msl.setText("У цьому чаті найкращий гравець, це ви. Ви відправили " + kills + " " + word + " на там той світ.");
                } else {
                    String[] top = Database.getTopChat(message);
                    String messageText = "Топ користувачів бота у цьому чаті: \n \n";
                    for (int i = 0; i < 10; i++) {
                        if (top[i].equals("")) break;
                        String lnTxt = "";
                        lnTxt += i + 1;
                        lnTxt += ". " + Database.getNicknameByID(top[i]) + " — " + Database.getKillsByID(top[i]) + "\n";
                        messageText += lnTxt;
                    }
                    msl.setText(messageText);
                }
            } else if (text.startsWith("/setname") || text.startsWith("/setname@dead_kacap_counter_bot")) {
                String[] args = text.split(" ");
                if (args.length > 1) {
                    String resNick = "";
                    for (int i = 1; i < args.length; i++) {
                        resNick+=args[i];
                        if (i != args.length - 1) {
                            resNick+=" ";
                        }
                    }
                    if (resNick.length() > 24) {
                        msl.setText("Ім'я занадто довге, виберіть по менше(не більше 24 символів)");
                    } else {
                        Database.setNewNickname(message, resNick);
                        msl.setText("Ваш нік успішно змінено на: " + resNick);
                    }
                } else {
                    msl.setText("Щоб змінити своє ім'я у боті, використайте /setname і в цьому самому повідомленні допишіть ім'я яке ви бажаєте(не більше 24 символів).");
                }
            } else if (text.equals("/print") && message.getFrom().getId() == 964809259) {
                Database.printData();
                msl.setText("Зроблено");
            } else {
                return;
            }
            messageSender.sendMessage(msl);
        }
    }
}
