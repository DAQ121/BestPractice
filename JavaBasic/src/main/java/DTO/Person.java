package DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * grade<p>Title: Student</p>
 * <p>Description: 学生实体类</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/5/20</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */

@Data
public class Person {

    public String name;

    public Integer age;

    public String sex;

    public Integer grade;

    public Integer money;

    public List<Student> products;

}
