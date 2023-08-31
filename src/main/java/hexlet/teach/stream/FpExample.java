package hexlet.teach.stream;

/**
 * @author andreiserov
 */
public class FpExample {
    public static void main(String[] args) {
        final Stub stub = new Stub("start value", "start value");
        System.out.println(stub);

        final Stub newStub = convert(stub, "new value");
        System.out.println(stub);
    }

    static Stub convert(Stub stub, String valueToChange) {
        return new Stub(valueToChange, stub.b);
    }

}

class Stub {
    String a;
    String b;

    public Stub(String a, String b) {
        this.a = a;
        this.b = b;
    }


}
