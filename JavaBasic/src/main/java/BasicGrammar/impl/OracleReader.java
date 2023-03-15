package BasicGrammar.impl;

import BasicGrammar.AbstractDB;

public class OracleReader extends AbstractDB {

    @Override
    public void readConfig() {
        System.out.println("子类的实现");
    }

    @Override
    public void witeConfig() {
        System.out.println("子类要实现");
    }

    @Override
    protected void testfunc() {

    }
}
