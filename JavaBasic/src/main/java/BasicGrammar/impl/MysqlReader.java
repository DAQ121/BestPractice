package BasicGrammar.impl;

import BasicGrammar.Reader;
import BasicGrammar.Writer;

public class MysqlReader implements Reader, Writer {

    @Override
    public void readConfig() {
        System.out.println("普通类要实现方法");
    }

    @Override
    public void witeConfig() {

    }
}
