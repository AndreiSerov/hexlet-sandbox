//package hexlet.teach.stream;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.List;
//
///**
// * @author andreiserov
// */
//public class ObjectDemo {
//
//    private static final List<User> users = List.of(
//        new User(
//            "vasya",
//            "asdfqwerzxcv",
//            new Address(
//                "home",
//                "Testovskaya",
//                1,
//                1234
//            )
//        ),
//        new User(
//            "petya",
//            "asdfqwerzxcv",
//            new Address(
//                "home",
//                "tverskaya",
//                1,
//                1234
//            )
//        ),
//        new User(
//            "fedia",
//            "asdfqwerzxcv",
//            new Address(
//                "home",
//                "Testovskaya",
//                1,
//                1234
//            )
//        )
//    );
//
//
//
//    public static void main(String[] args) {
//
//        final String s = "2147483648";
//        final int i = Integer.parseInt(s);
//
//        System.out.println(i);
////        final ObjectMapper om = new ObjectMapper();
////
////        users.stream()
////                .filter(it -> it.address().street().equalsIgnoreCase("testovskaya"))
////                .toList();
//
////        om.writeValueAsString();
//    }
//}
//
//
////public record User (
////    String login,
////    String password,
////    Address address
////) {}
//
//
//record Address (
//    String type,
//    String street,
//    Integer house,
//    Integer flat
//) {}
//
