package ApachCommonUtils;

public class TestTime {

    public static void main(String[] args) throws InterruptedException {

        int i = 0;

        while (i < 10000000) {
            testBytes();
            Thread.sleep(50);
            i ++;
        }
    }

    public static byte[] testBytes() {
        byte[] bytes = new byte[1000];

        for (int i = 0; i < 1000; i++) {
            bytes[i] = (byte) i;
        }

        return bytes;
    }



}
