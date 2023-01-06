package auiechko.deadkacapbot.handlers;

import auiechko.deadkacapbot.messagesender.MessageSender;
import auiechko.deadkacapbot.database.Database;

import com.google.common.base.StandardSystemProperty;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.sql.SQLOutput;
import java.util.List;

@Component
public class MessageHandler implements Handler<Message>{

    private final MessageSender messageSender;
    private final Database database;

    public MessageHandler(MessageSender messageSender, Database database) {
        this.messageSender = messageSender;
        this.database = database;
    }

    @Override
    public void choose(Message message) {
        if(message.hasText()) {
            boolean user = database.findUser(message);
            if (!user) {
                database.addUser(message);
            }
            String text = message.getText();
            SendMessage msl = new SendMessage();
            msl.setChatId(message.getChatId());
            if(text.equals("/shot") || text.equals("/shot@dead_kacap_counter_bot")) {
                if (message.getDate() - database.getLastUse(message) >= 3600) {
                    long a = (long) (Math.random() * 91) + 10;
                    String word = "кацапів";
                    if (a % 10 == 1 && a != 11) { word = "кацапа"; }
                    long newKills = database.getKills(message) + a;
                    database.setKills(message, newKills);
                    database.setLastUse(message, (long) message.getDate());
                    long newShots = database.getShots(message) + 1;
                    database.setShots(message, newShots);
                    String d = database.getExplicit(message) ? "до смерті охуїли" : "подохли";
                    msl.setText("Постріл! \nБавовни багато завдяки " + database.getNickname(message) + ". \nВід твоїх рук " + d + " " + a + " " + word + "!");
                } else {
                    long time = database.getLastUse(message) + 3600 - message.getDate();
                    long minutes = time / 60;
                    long seconds = time % 60;
                    msl.setText("Відпочинь, треба перезарядити хаймарс. \nСтріляй через " + minutes + "хв. " + seconds + "c.");
                }
            } else if (text.equals("/myrank")  || text.equals("/myrank@dead_kacap_counter_bot") || text.equals("/profile") || text.equals("/profile@dead_kacap_counter_bot") || text.equals("/stats") || text.equals("/stats@dead_kacap_counter_bot")) {
                String word = "кацапів";
                long kills = database.getKills(message);
                long shots = database.getShots(message);
                if (kills % 10 == 1 && kills != 11) { word = "кацапа"; }
                if (shots == 0) {
                    shots++;
                }
                msl.setText(database.getNickname(message) + ". Тобою відправлено " + kills + " " + word + " на той світ. \nКількість пострілів: " + shots + ". Середня кількість мертвих кацапів за твій постріл: " + kills / shots + ".");
            } else if (text.equals("/start")) {
                msl.setText("Готовий до бою! \n \nВся твоя суть це запускати мене, а я підрахую скільки кацапів попали на концерт кобзона без черги.");
            } else if (text.equals("/top") || text.equals("/top@dead_kacap_counter_bot") ) {
                String[] top = database.getTop(message);
                StringBuilder messageText = new StringBuilder("Топ користувачів бота: \n \n");
                for (int i = 0; i < 10; i++) {
                    String lnTxt = "";
                    lnTxt += i + 1;
                    lnTxt += ". " + database.getNicknameByID(top[i]) + " — " + database.getKillsByID(top[i]) + "\n";
                    messageText.append(lnTxt);
                }
                msl.setText(messageText.toString());
            } else if (text.equals("/topchat") || text.equals("/topchat@dead_kacap_counter_bot")) {
                if (message.getChat().isUserChat()) {
                    String word = "кацапів";
                    long kills = database.getKills(message);
                    if (kills % 10 == 1 && kills != 11) { word = "кацапа"; }
                    msl.setText("У цьому чаті найкращий гравець, це ви. Ви відправили " + kills + " " + word + " на там той світ.");
                } else {
                    String[] top = database.getTopChat(message);
                    StringBuilder messageText = new StringBuilder("Топ користувачів бота у цьому чаті: \n \n");
                    for (int i = 0; i < 10; i++) {
                        if (top[i] == null || top[i].equals("")) break;
                        String lnTxt = "";
                        lnTxt += i + 1;
                        lnTxt += ". " + database.getNicknameByID(top[i]) + " — " + database.getKillsByID(top[i]) + "\n";
                        messageText.append(lnTxt);
                    }
                    msl.setText(messageText.toString());
                }
            } else if (text.startsWith("/setname") || text.startsWith("/setname@dead_kacap_counter_bot")) {
                String[] args = text.split(" ");
                if (args.length > 1) {
                    StringBuilder resNick = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        resNick.append(args[i]);
                        if (i != args.length - 1) {
                            resNick.append(" ");
                        }
                    }
                    if (resNick.length() > 24) {
                        msl.setText("Ім'я занадто довге, виберіть по менше(не більше 24 символів)");
                    } else {
                        database.setNewNickname(message, resNick.toString());
                        msl.setText("Ваш нік успішно змінено на: " + resNick);
                    }
                } else {
                    msl.setText("Щоб змінити своє ім'я у боті, використайте /setname і в цьому самому повідомленні допишіть ім'я яке ви бажаєте(не більше 24 символів).");
                }
            } else if (text.equals("/print") && message.getFrom().getId() == 964809259) {
                database.printData();
                msl.setText("Зроблено");
            } else if (text.startsWith("/add") && message.getFrom().getId() == 964809259) {
                String[] args = text.split(" ");
                if (args.length > 1) {
                    long id = Long.parseLong(args[1]);
                    long a = Long.parseLong(args[2]);
                    long newKills = database.getKillsByID(String.valueOf(id)) + a;
                    database.setKillsById(id, newKills);
                }
                msl.setText("Додано до ");
            } else if (text.equals("/explicit") || text.equals("/explicit@dead_kacap_counter_bot")) {
                database.changeExplicit(message);
                String nff = database.getExplicit(message) ? "увімкнули" : "вимкнули";
                msl.setText("Ви " + nff + " матюки");
            } else {
                return;
            }
            messageSender.sendMessage(msl);
        } else if (message.getNewChatMembers().size() > 0) {
            List<User> NewUsers = message.getNewChatMembers();
            SendMessage msl = new SendMessage();
            msl.setChatId(message.getChatId());
            msl.setText("Привіт! " + NewUsers.get(0).getFirstName() + ", я — бот який вбиває кацапів, можеш спробувати мене, використавши команду /shot." );
            msl.setChatId(message.getChatId());
            msl.setReplyMarkup(msl.getReplyMarkup());
            msl.setReplyToMessageId(message.getMessageId());
            messageSender.sendMessage(msl);
        }
    }
}
