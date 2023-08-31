package hexlet.teach.archive;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author andreiserov
 */

class University {
    private List<Faculty> faculties;
    private LocalDateTime holiday = LocalDateTime.now();

    public University(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    @Deprecated
    private University() {
    }

    public University(List<Faculty> faculties, LocalDateTime holiday) {
        this.faculties = faculties;
        this.holiday = holiday;
    }

    public static University create() {
        return new University();
    }

    public void graduate() {
        System.out.println("Today is holiday " + holiday.toString());
    }


    final public void closeFaculty(Faculty faculty) {
        faculties.remove(faculty);
    }

}

class PhysicsNii extends University {
    private List<String> equipName;

    public PhysicsNii(List<Faculty> faculties, List<String> equipName) {
        super(faculties);
        this.equipName = equipName;
    }

    @Override
    public void graduate() {
        System.out.println("Its our special holiday Today");
    }
}


class Faculty {
    private String name;

    public Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class App {

    public static void main(String[] args) {

        final Faculty biology = new Faculty("Biology");
        final Faculty chemistry = new Faculty("chemistry");

        final University university = new University(
            List.of(
                biology, chemistry
            )
        );

        university.closeFaculty(biology);





    }
}
