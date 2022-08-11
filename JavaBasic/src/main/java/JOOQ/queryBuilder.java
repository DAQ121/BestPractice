package JOOQ;

import DTO.Student;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * <p>Title: queryBuilder</p>
 * <p>Description: </p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/6/25</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class queryBuilder {

    public static void main(String[] args) {
        DSLContext mysql = DSL.using(SQLDialect.MYSQL);
        DSLContext oracle = DSL.using(SQLDialect.ORACLE11G);
        DSLContext sqlserver = DSL.using(SQLDialect.SQLSERVER);
        DSLContext postgres = DSL.using(SQLDialect.POSTGRES);

        System.out.println(mysql.select().from("sys").getSQL());
        System.out.println(oracle.select().from("daq").getSQL());
        System.out.println(mysql.select(DSL.field("u.id"), DSL.field("u.name"), DSL.field("b.book_name"))
                .from(DSL.table("user").as("u"))
                .join(DSL.table("book").as("b"))
                .on(DSL.field("u.id").eq(DSL.field("b.author_id")))
                .where(DSL.field("u.name").eq("Elvis Wang"))
                .getSQL(ParamType.INLINED));

        System.out.println(oracle.select(DSL.field("u.id"), DSL.field("u.name"), DSL.field("b.book_name"))
                .from(DSL.table("user").as("u"))
                .join(DSL.table("book").as("b"))
                .on(DSL.field("u.id").eq(DSL.field("b.author_id")))
                .where(DSL.field("u.name").eq("Elvis Wang"))
                .getSQL(ParamType.INLINED));

        System.out.println(sqlserver.select(DSL.field("u.id"), DSL.field("u.name"), DSL.field("b.book_name"))
                .from(DSL.table("user").as("u"))
                .join(DSL.table("book").as("b"))
                .on(DSL.field("u.id").eq(DSL.field("b.author_id")))
                .where(DSL.field("u.name").eq("Elvis Wang"))
                .getSQL(ParamType.INLINED));

        System.out.println(postgres.select(DSL.field("u.id"), DSL.field("u.name"), DSL.field("b.book_name"))
                .from(DSL.table("user").as("u"))
                .join(DSL.table("book").as("b"))
                .on(DSL.field("u.id").eq(DSL.field("b.author_id")))
                .where(DSL.field("u.name").eq("Elvis Wang"))
                .getSQL(ParamType.INLINED));

    }
}
