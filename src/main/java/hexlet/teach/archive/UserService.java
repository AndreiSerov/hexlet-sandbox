//package hexlet.teach;
//
//import hexlet.teach.stream.User;
//
///**
// * @author andreiserov
// */
//public class UserService {
//
//    private UserRepo userRepo;
//    private UserProfileRepo userProfileRepo;
//
//    public User create(User user) throws Exception {
//
//        if (userRepo.exist(user)) throw new Exception("User exists");
//
//        userRepo.save(user);
//
//        return user;
//    }
//}
