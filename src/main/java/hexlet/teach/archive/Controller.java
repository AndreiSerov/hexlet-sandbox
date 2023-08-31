//package hexlet.teach;
//
//import hexlet.teach.stream.User;
//
///**
// * @author andreiserov
// */
//public class Controller {
//
//    private UserService service;
//
//
//    /**
//     * method for user creation
//     */
//    public Response<User> create(UserDto dto) {
//
//        var user = UserMapper.toEntity(dto);
//
//        try {
//            return Response.ok(service.create(user))   ;
//        } catch (Exception e) {
//            return Response.error(401);
//        }
//    }
//}
