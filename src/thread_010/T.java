package thread_010;
import java.util.concurrent.TimeUnit;

/**
 * 在子类的synchronized方法里面,
 * 能否调用父类的同步方法
 *
 * 可以的，因为锁住的是同一个对象
 *
 */
public class T {
    synchronized void m(){
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end");
    }
    public static void main(String []args){
        new TT().m();
    }
}

class TT extends T{
    synchronized void m(){
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }
}
