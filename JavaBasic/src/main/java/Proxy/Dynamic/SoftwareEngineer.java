package Proxy.Dynamic;

/**
* 动态代理委托类实现， 实现接口 Person。 被动态生成的代理类代理
*/

public class SoftwareEngineer implements Person{

   @Override
   public void generateName(String name) {
       System.out.println(name);
   }
}