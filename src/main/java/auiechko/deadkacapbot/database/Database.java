package auiechko.deadkacapbot.database;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

@Component
public class Database {
    static String src = "json/users.json";
    static File userJsonFile = new File(src);

    public static void addUser(Message message) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject userInfoJSON = new JSONObject();
            userInfoJSON.put("kills",0);
            userInfoJSON.put("shots",0);
            userInfoJSON.put("lastUse",0);
            userInfoJSON.put("boxes",0);
            userInfoJSON.put("nickname",message.getFrom().getFirstName());
            userInfoJSON.put("explicit",false);
            usersJSON.put(message.getFrom().getId().toString(),userInfoJSON);
            FileWriter writeFile = new FileWriter(src);
            writeFile.write(usersJSON.toString());
            writeFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long getBoxes(Message message) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject infoJSON = usersJSON.getJSONObject(message.getFrom().getId().toString());
            return infoJSON.getLong("boxes");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getKills(Message message) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject infoJSON = usersJSON.getJSONObject(message.getFrom().getId().toString());
            return infoJSON.getLong("kills");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getLastUse(Message message) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject infoJSON = usersJSON.getJSONObject(message.getFrom().getId().toString());
            return infoJSON.getLong("lastUse");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getShots(Message message) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject infoJSON = usersJSON.getJSONObject(message.getFrom().getId().toString());
            return infoJSON.getLong("shots");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void setBoxes(Message message, long newValue) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject userInfoJSON = usersJSON.getJSONObject(message.getFrom().getId().toString());
            userInfoJSON.put("kills",userInfoJSON.getLong("kills"));
            userInfoJSON.put("shots",userInfoJSON.getLong("shots"));
            userInfoJSON.put("lastUse",userInfoJSON.getLong("lastUse"));
            userInfoJSON.put("boxes",newValue);
            usersJSON.put(message.getFrom().getId().toString(),userInfoJSON);
            FileWriter writeFile = new FileWriter(src);
            writeFile.write(usersJSON.toString());
            writeFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setLastUse(Message message, long newValue) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject userInfoJSON = usersJSON.getJSONObject(message.getFrom().getId().toString());
            userInfoJSON.put("kills",userInfoJSON.getLong("kills"));
            userInfoJSON.put("shots",userInfoJSON.getLong("shots"));
            userInfoJSON.put("lastUse",newValue);
            userInfoJSON.put("boxes",userInfoJSON.getLong("boxes"));
            usersJSON.put(message.getFrom().getId().toString(),userInfoJSON);
            FileWriter writeFile = new FileWriter(src);
            writeFile.write(usersJSON.toString());
            writeFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setShots(Message message, long newValue) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject userInfoJSON = usersJSON.getJSONObject(message.getFrom().getId().toString());
            userInfoJSON.put("kills",userInfoJSON.getLong("kills"));
            userInfoJSON.put("lastUse",userInfoJSON.getLong("lastUse"));
            userInfoJSON.put("shots",newValue);
            userInfoJSON.put("boxes",userInfoJSON.getLong("boxes"));
            usersJSON.put(message.getFrom().getId().toString(),userInfoJSON);
            FileWriter writeFile = new FileWriter(src);
            writeFile.write(usersJSON.toString());
            writeFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setKills(Message message, long newValue) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject userInfoJSON = usersJSON.getJSONObject(message.getFrom().getId().toString());
            userInfoJSON.put("lastUse",userInfoJSON.getLong("lastUse"));
            userInfoJSON.put("shots",userInfoJSON.getLong("shots"));
            userInfoJSON.put("kills",newValue);
            userInfoJSON.put("boxes",userInfoJSON.getLong("boxes"));
            usersJSON.put(message.getFrom().getId().toString(),userInfoJSON);
            FileWriter writeFile = new FileWriter(src);
            writeFile.write(usersJSON.toString());
            writeFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean findUser(Message message) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject userInfoJSON = usersJSON.getJSONObject(message.getFrom().getId().toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String[] getTop(Message message) {
        String[] top = new String[10];
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            for (int i = 0; i < top.length; i++) {
                long currentKillsLoop = -1;
                for (Iterator<String> key = usersJSON.keys(); key.hasNext();) {
                    String id = String.valueOf(key.next());
                    JSONObject name = (JSONObject) usersJSON.get(id);
                    if(name.getLong("kills") > currentKillsLoop) {
                        top[i] = id;
                        currentKillsLoop = name.getLong("kills");
                    }
                }
                JSONObject user = (JSONObject) usersJSON.get(top[i]);
                user.put("kills",-1L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return top;
    }

    public static long getKillsByID(String userId) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject infoJSON = usersJSON.getJSONObject(userId);
            return infoJSON.getLong("kills");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getNicknameByID(String userId) {
        String nick = "Ім'я невідоме";
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject infoJSON = usersJSON.getJSONObject(userId);
                return infoJSON.getString("nickname");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nick;
    }

    public static String getNickname(Message message) {
        String nick = "Ім'я невідоме";
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                String scannedText = scannerJSON.nextLine();
                JSONtext.append(scannedText);
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject infoJSON = usersJSON.getJSONObject(String.valueOf(message.getFrom().getId()));
            return infoJSON.getString("nickname");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nick;
    }

    public static void setNewNickname(Message message, String resNick) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject infoJSON = usersJSON.getJSONObject(String.valueOf(message.getFrom().getId()));
            infoJSON.put("nickname",resNick);
            FileWriter writeFile = new FileWriter(src);
            writeFile.write(usersJSON.toString());
            writeFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static String[] getTopChat(Message message) {
        String[] top = new String[10];
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            for (int i = 0; i < top.length; i++) {
                long currentKillsLoop = -1;
                for (Iterator<String> key = usersJSON.keys(); key.hasNext();) {
                    String id = String.valueOf(key.next());
                    JSONObject name = (JSONObject) usersJSON.get(id);
                    boolean userInChat = checkChatUser(String.valueOf(message.getChatId()),Long.parseLong(id));
                    if(name.getLong("kills") > currentKillsLoop && userInChat) {
                        top[i] = id;
                        currentKillsLoop = name.getLong("kills");
                    }
                }
                JSONObject user = (JSONObject) usersJSON.get(top[i]);
                user.put("kills",-1L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return top;
    }

    private static boolean checkChatUser(String chatId, long userId) {
        try {
            new GetChatMember(chatId, userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void printData() {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            System.out.println(JSONtext.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void changeExplicit(Message message) {
        try {
            StringBuilder JSONtext = new StringBuilder();
            Scanner scannerJSON = new Scanner(new FileReader(src));
            while(scannerJSON.hasNextLine()) {
                JSONtext.append(scannerJSON.nextLine());
            }
            scannerJSON.close();
            JSONObject usersJSON = new JSONObject(JSONtext.toString());
            JSONObject user = (JSONObject) usersJSON.get(String.valueOf(message.getFrom().getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getExplicit(Message message) {
        return true;
    }
}
