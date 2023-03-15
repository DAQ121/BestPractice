package BasicGrammar.convert;

public class testConvert {
    public static void main(String[] args) {
        BasicEquip basicEquip = new BasicEquip();
        basicEquip.setAge(12);
        basicEquip.setName("daq");

        final Online online = new Online();
        online.setAge(23);
        online.setName("kk");

        BasicEquip base = (BasicEquip) online;

        System.out.println(base);
    }
}
