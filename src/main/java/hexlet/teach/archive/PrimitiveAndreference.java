package hexlet.teach.archive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author andreiserov
 */
public class PrimitiveAndreference {
    static int prim;
    static Integer ref;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        String word = scanner.next();
        final int i = Integer.parseInt(word);
        final Long ii = Long.parseLong(word);

        final List<Number> numbers = List.of(i, ii);


        System.out.println(prim);
        System.out.println(ref);
    }

    class PrimitiveAndreference2 {
        static int prim;
        static Integer ref;

        public static void main(String[] args) {
            System.out.println(prim);
            System.out.println(ref);
        }
    }
}


class PrimitiveAndreference3 {

    static ObjectMapper mapper = new ObjectMapper();

    // Что будет на месте homeNumber?
    // 1. программа не скомпилируется
    // 2. 0
    // 3. null

    static String json = """
            {
                "homeNumber": 79123456
            }
            """;

    public static void main(String[] args) throws Exception {
        final Phones phones = mapper.readValue(json, Phones.class);

        Optional.ofNullable(phones.getMobileNumber())
            .ifPresent(it -> { if (it == 10) System.out.println("exception"); });

        final Long mobile = Optional.ofNullable(phones.getMobileNumber())
            .orElseThrow(() -> new RuntimeException("Poniatnaya oshibka"));

        if (phones.getMobileNumber() != null && phones.getMobileNumber() == 10) {
            System.out.println("exception");
        }

        System.out.println(phones);

        final PersonData personData = new PersonData(phones, List.of(phones));

        final Long aLong = Optional.of(personData)
            .map(PersonData::getMainPhone)
            .map(Phones::getMobileNumber)
            .orElse(10L);


    }
//    class Phones {
//        int homeNumber;
//        Long mobileNumber;
//    }
}

class PersonData {
    public PersonData(Phones mainPhone, List<Phones> phones) {
        this.mainPhone = mainPhone;
        this.phones = phones;
    }

    public Phones getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(Phones mainPhone) {
        this.mainPhone = mainPhone;
    }

    Phones mainPhone;

    List<Phones> phones;

    public List<Phones> getPhones() {
        return phones;
    }

    public void setPhones(List<Phones> phones) {
        this.phones = phones;
    }

    public PersonData(List<Phones> phones) {
        this.phones = phones;
    }
}

class Phones {
    int homeNumber;

    @Nullable
    Long mobileNumber;



    public int getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "Phones{" +
            "homeNumber=" + homeNumber +
            ", mobileNumber=" + mobileNumber +
            '}';
    }
}

class PrimitiveAndreference4 {

    // 1. программа не скомпилируется
    // 2. 0
    // 3. 2147483649
    // 4. -2147483649

    public static void main(String[] args) {
        long num = 2_147_483_647;
        final long number = Long.parseLong("9223372036854775807");
        System.out.println(number);
    }
}

class PrimitiveAndreference5 {

    // 1. программа не скомпилируется
    // 2. 0
    // 3. 2147483649
    // 4. -2147483649

    public static void main(String[] args) {
        final long number = Integer.parseInt("2147483649");
        // 111 + 1 000
        System.out.println(number + 100002);
    }
}

class PrimitiveAndreference6 {
    static String json = """
            {
                "mobileNumber": 2147483649
            }
            """;


    static ObjectMapper mapper = new ObjectMapper();

    // Что будет на месте mobileNumber?
    // 1. программа не скомпилируется
    // 2. 0
    // 3. null
    public static void main(String[] args) throws JsonProcessingException {
        final PhonesRef phones = mapper.readValue(json, PhonesRef.class);
        System.out.println(phones);
        System.out.println(mapper.writeValueAsString(phones));
    }
//    class PhonesRef {
//        Integer homeNumber;
//        Long mobileNumber;
}


class PrimitiveAndreference7 {
    public static void main(String[] args) {

        String str = "ASDFQWER";
//        Character
        for (char c : str.toCharArray()) {
            System.out.print(c - 32);

            System.out.println(c - 32);
        }
        char ch = 'a';

        char newCh = 'a' - 32;

        System.out.println(newCh);

    }
}

class PhonesRef {
    Integer homeNumber;
    Long mobileNumber;

    public Integer getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(Integer homeNumber) {
        this.homeNumber = homeNumber;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "Phones{" +
            "homeNumber=" + homeNumber +
            ", mobileNumber=" + mobileNumber +
            '}';
    }
}


