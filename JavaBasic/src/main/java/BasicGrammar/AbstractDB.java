package BasicGrammar;

public abstract class AbstractDB implements Reader, Writer {

    private final String TITLE = "抽象类";

    protected abstract void testfunc();

    private void privFunc() {
        System.out.println("这是抽象类的私有方法");
    }

    protected void protectFunc() {
        System.out.println("这是抽象类的保护方法");
    }

    @Override
    public void ReaderDefaultFunc() {
        Reader.super.ReaderDefaultFunc();
    }

    @Override
    public void WriteDefaultFUnc() {
        Writer.super.WriteDefaultFUnc();
    }
}
