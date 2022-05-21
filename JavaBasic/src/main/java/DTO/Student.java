package DTO;

import lombok.Builder;
import lombok.Data;

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
@Builder
public class Student {

    public String name;

    public Integer age;

    public String sex;

    public Integer grade;

}
