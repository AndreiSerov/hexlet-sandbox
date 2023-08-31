package hexlet.teach.archive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author andreiserov
 */
public class EqualsDemo {

    public static void main(String[] args) {
        final Person vasya = new Person("Vasya", "Vasin");
        final Person petya = new Person("Petya", "Petrov");
        final Person petya2 = new Person("Petya", "Petrov");
        var petyaClone = petya;

        boolean eq1 = vasya.equals(petya);      // nameEquals and surnameEquals
        boolean eq2 = petya.equals(petya2);     // nameEquals and surnameEquals
        final boolean kalia_ = petya.name.equals("kalia ");
        final boolean lkjasdf = petya.surname.equals("lkjasdf");
        boolean eq3 = petya.equals(petyaClone); // == cause

//        vasya.field = "Petya";
//        vasya.surname = "Petrov";
//        boolean eq4 = vasya.equals(petya);      // nameEquals and surnameEquals


        final var vasyaEmb = new PersonEmbeddedEquals("Vasya", "Vasin");
        final var petyaEmb = new PersonEmbeddedEquals("Petya", "Petrov");
        final var petya2Emb = new PersonEmbeddedEquals("Petya", "Petrov");

        boolean eq1Emb = vasyaEmb.equals(petyaEmb);
        boolean eq2Emb = petyaEmb.equals(petya2Emb);

//        vasyaEmb.field = "Petya";
//        vasyaEmb.surname = "Petrov";
//        boolean eq3Emb = vasyaEmb.equals(petyaEmb);


        final int vasyaHashcode = vasya.hashCode();
        final int petyaHashcode = petya.hashCode();
        final int petya2Hashcode = petya2.hashCode();

        final int vasyaHashcodeEmb = vasyaEmb.hashCode();
        final int petyaHashcodeEmb = petyaEmb.hashCode();
        final int petya2HashcodeEmb = petya2Emb.hashCode();


        final var vasyaRecord = new PersonRecord("Vasya", "Vasin");
        final var petyaRecord = new PersonRecord("Petya", "Petrov");
        final var petya2Record= new PersonRecord("Petya", "Petrov");

        final int vasyaHashcodeRecord = vasyaRecord.hashCode();
        final int petyaHashcodeRecord = petyaRecord.hashCode();
        final int petya2HashcodeRecord = petya2Record.hashCode();


        petyaClone.name = "Petya Clone now";
//        boolean eq4 = vasya == petya;


    }

}


class Person {

    @NotNull
    public String name;

    @Nullable
    public String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        final boolean isInstance = o instanceof Person;
        if (!isInstance) return false;

        final Person oPerson = (Person) o;

        final boolean isNamesEquals = this.name.equals(oPerson.name);
        final boolean isSurnamesEquals = this.surname != null && this.surname.equals(oPerson.surname);
        if (isNamesEquals && isSurnamesEquals ) return true;

        return false;
    }

    @Override
    public int hashCode() {
        int result = name != null ? 71*name.length()*name.charAt(0) : 0;
        result = 31 * result + (surname != null ? 11*surname.length() : 0);
        return result;
    }
}

class PersonEmbeddedEquals {
    private static boolean verySeriousCondition = false;
    public String name;
    public String surname;

    public PersonEmbeddedEquals(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public static void main(String[] args) {
        String str = null;


        final Optional<String> optStr = Optional.ofNullable(str);


        final List<Integer> list = List.of(1, 2, 3);



        char[] chars;
        if (optStr.isPresent()) {
            chars = optStr.get().toCharArray();
        }

        if (Objects.equals(str, "kalia balia")) System.out.println("String is kalia balia");


        final char character = (char) 2000;
        System.out.println((int) '-');
        System.out.println((int) ' ');
        System.out.println((int) ',');
        System.out.println((int) ';');
        System.out.println((int) ' ');

        System.out.println(character);



        final PersonRecord obj = new PersonRecord("field", null);
        if (obj.field().equals("kalia balia")) {
            doSomething();
        }
    }

    static <T> List<T> collectionMethod(Collection<T> input) {
        if (verySeriousCondition)
            return List.of();
        // other logic


        return input.stream().toList();
    }

    @Nullable
    private static String doSomething() {
        return false ? "kalia balia" : null;
    }
}


record PersonRecord(
    @NotNull
    String field,
    @Nullable
    String surname
) {}


class PersonA {
    PersonA() {}

    public static void main(String[] args) {
        final PersonA vasya = new PersonA();
        vasya.id = 1;
        vasya.name = "Vasya";

        final Document vasyaDoc = new Document();
        vasyaDoc.number = "1234";
        vasyaDoc.series = "123";
        vasyaDoc.owner = vasya;
        vasya.documents = List.of(vasyaDoc);

        final PersonA vasya2 = new PersonA();
        vasya2.id = 1;
        vasya2.name = "Vasya";

        final Document vasyaDoc2 = new Document();
        vasyaDoc2.number = "1234";
        vasyaDoc2.series = "123";
        vasyaDoc2.owner = vasya2;
        vasya2.documents = List.of(vasyaDoc2);

        final boolean equals = vasyaDoc2.equals(vasyaDoc);

        String stub;
        System.out.println("tratta");
    }
    int id;
    String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonA personA = (PersonA) o;

        if (id != personA.id) return false;
        if (name != null ? !name.equals(personA.name) : personA.name != null) return false;
        return documents != null ? documents.equals(personA.documents) : personA.documents == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (documents != null ? documents.hashCode() : 0);
        return result;
    }

    // one to many
    List<Document> documents;
}

class Document {
    Document() {}

    String series;
    String number;

    // One to One
    PersonA owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (series != null ? !series.equals(document.series) : document.series != null) return false;
        return number != null ? !number.equals(document.number) : document.number != null;
    }

    @Override
    public int hashCode() {
        int result = series != null ? series.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}


class MapDemo {
    public static void main(String[] args) {
        final Inner one = new Inner(1);
        final Inner two = new Inner(2);
        final Inner sameOne = new Inner(1);

        Map map = new HashMap<Inner, String>();
        map.put(one, "one");
        map.put(two, "two");
        map.put(sameOne, "sameOne");

        map.put(sameOne, "kalia");

//        map.get()
    }

    static class Inner {
        int number;

        Inner(int number) {
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return false;
            if (o == null || getClass() != o.getClass()) return false;

            Inner inner = (Inner) o;

            return false;
//            return number == inner.number;
        }

        @Override
        public int hashCode() {
            return number;
        }
    }

}