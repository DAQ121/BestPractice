package Java8.Function;

import DTO.Student;

@FunctionalInterface
public interface MyFunction {

    void show(Student student, Integer count);

}