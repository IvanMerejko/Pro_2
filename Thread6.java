import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Thread6 extends Thread {
    private int i ;

    private Buffer m_buffer;
    private CyclicBarrier m_barrier;
    private ReentrantLock m_mutex;
    private Integer m_secondValue;

    Thread6(CyclicBarrier barrier , Buffer buffer , ReentrantLock mutex , Integer secondValue){
        super("Thread6");
        m_barrier = barrier;
        m_buffer = buffer;
        m_mutex = mutex;
        m_secondValue = secondValue;
        i = 1;
        start();
    }

    @Override
    public void run(){

        while (true){

            try
            {
                System.out.println(getName() + " waits before barrier");
                m_barrier.await();

            }catch(BrokenBarrierException e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
                break;
            }
            catch(InterruptedException e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
                break;
            }
            System.out.println(getName() + " works after barrier");


            m_mutex.lock();
            System.out.println(getName() + " lock mutex");
            m_secondValue++;
            System.out.println(getName() + " modifier value");
            System.out.println(getName() + " unlock mutex");
            m_mutex.unlock();
//            try{
//                Thread.sleep(5000);
//            } catch (InterruptedException e){
//                System.out.println(e.getMessage());
//            }
            ++i;
            System.out.println(" i = " + i);
            if(i > 5){
                m_barrier.reset();
                System.out.println("m_barrier reset");

                break;
            }
        }


    }
}
