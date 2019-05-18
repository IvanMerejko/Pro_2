import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Thread5 extends Thread {
    private int i =0;
    private Buffer m_buffer;
    private ReentrantLock m_mutex;
    private Integer m_secondValue;
    private Semaphore m_init;
    private Semaphore m_wait;


    Thread5(Buffer buffer , Semaphore init , Semaphore wait , ReentrantLock mutex , Integer second_value){
        super("Thread5");
        m_buffer = buffer;
        m_init = init;
        m_mutex = mutex;
        m_wait = wait;
        m_secondValue = second_value;
        start();
    }

    @Override
    public void run(){
        Random rnd = new Random(System.currentTimeMillis());

        while (true){
            System.out.println(getName() + " sent semaphore ");
            m_init.release();

            System.out.println(getName() + " wait another semaphore ");
            try{
                if(!Main.isP5WorkAlong){
                    m_wait.acquire();

                }

            } catch (InterruptedException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            System.out.println(getName() + " works after semaphore");

            if(!Main.isP5WorkAlong){
                int value = rnd.nextInt(100);
                m_buffer.push(value);
                System.out.println(getName() + " push value :" + value );
            }






            ++i;

            m_mutex.lock();
            System.out.println(getName() + " lock mutex");
            System.out.println(getName() + " use mutex and use value  :" + m_secondValue );
            System.out.println(getName() + " unlock mutex");
            m_mutex.unlock();
//            try{
//                Thread.sleep(5000);
//            } catch (InterruptedException e){
//                System.out.println(e.getMessage());
//            }
            System.out.println(i);
            if(i > 10000){
                break;
            }
        }
    }
}
