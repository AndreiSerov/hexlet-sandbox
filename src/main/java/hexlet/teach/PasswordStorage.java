package hexlet.teach;

import java.util.HashMap;
import java.util.Map;

/**
 * @author andreiserov
 */
public class PasswordStorage {
    private final Map<String, String> passwords;

    {
        passwords = new HashMap<>();
        passwords.put("google", "google_pass");
        passwords.put("yandex", "yandex_pass");
        passwords.put("youtube", "youtube_pass");
        passwords.put("instagram", "instagram_pass");
    }

    // CRUD - create read update delete

    public String save(String name, String password) {
        // save == update

        return passwords.put(name, password);
    }

    public String findByName(String name) {
        return passwords.get(name);
    }
// obj1 = new A("String")
// obj2 = new A("String")
//
//             ==  equals

    public String deleteByName(String name) {
        return passwords.remove(name);
    }
}

