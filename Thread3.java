import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Thread3 extends Thread {
    private Buffer m_buffer;
    private CyclicBarrier m_barrier;
    private CyclicBarrier m_barrier2;
    private ReentrantLock m_mutex;
    private Integer m_secondValue;

    Thread3(CyclicBarrier barrier , CyclicBarrier barrier2 , Buffer buffer , ReentrantLock mutex , Integer secondValue){
        super("Thread3");
        m_barrier = barrier;
        m_barrier2 = barrier2;
        m_buffer = buffer;
        m_mutex = mutex;
        m_secondValue = secondValue;
        start();
    }

    @Override
    public void run(){

        while (true){
            try
            {
                System.out.println(getName() + " waits before barrier(P2)");

                m_barrier.await() ;


            }catch(BrokenBarrierException e)
            {
                m_barrier2.reset();
                System.out.println(e.getMessage());
                break;
            }
            catch(InterruptedException e)
            {
                m_barrier2.reset();
                System.out.println(e.getMessage());
                break;
            }
            System.out.println(getName() + " works after barrier(P2)");


            m_mutex.lock();
            System.out.println(getName() + " lock mutex");
            System.out.println(getName() + " use value : " + m_secondValue);
            System.out.println(getName() + " unlock mutex");
            m_mutex.unlock();
//            try{
//                Thread.sleep(5000);
//            } catch (InterruptedException e){
//                System.out.println(e.getMessage());
//            }

            try
            {
                System.out.println(getName() + " waits before barrier(P6)");

                if(!Main.isP6Closed){
                    m_barrier2.await();
                } else {
                    throw new BrokenBarrierException();
                }

            }catch(BrokenBarrierException e)
            {
                m_barrier.reset();
                Main.isP3Closed = true;
                System.out.println(e.getMessage());
                e.printStackTrace();
                break;
            }
            catch(InterruptedException e)
            {
                m_barrier.reset();
                System.out.println(e.getMessage());
                e.printStackTrace();
                break;
            }
            System.out.println(getName() + " works after barrier(P6)");
        }


    }
}
