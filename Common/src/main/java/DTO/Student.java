package DTO;

import lombok.Builder;

import java.io.Serializable;

/**
 * grade<p>Title: Student</p>
 * <p>Description: 学生实体类</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/5/20</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
@Builder
public class Student implements Serializable {
    public Student() {
    }

    public Student(String name, Integer age, String sex, Integer grade) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.grade = grade;
    }

    public String name;

    public Integer age;

    public String sex;

    public Integer grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
