package builder;

import DTO.Student;

public class testBuilder {

    public static void main(String[] args) {
        Student daq = Builder.builder(Student::new)
                .with(Student::setName, "daq")
                .with(Student::setGrade, 120)
                .build();

        System.out.println(daq);

    }


}
