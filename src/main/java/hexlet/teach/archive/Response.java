package hexlet.teach.archive;

/**
 * @author andreiserov
 */
public class Response<T> {

    public static <T> Response<T> error(Integer code) {
        return null;
    }

    public static <T> Response<T> ok(T body) {
        return null;
    }

}
