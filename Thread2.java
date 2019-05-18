import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

public class Thread2 extends Thread {
    private Buffer m_buffer;
    private CyclicBarrier m_barrier;
    private Semaphore m_init;
    private Semaphore m_wait;

    Thread2(CyclicBarrier barrier , Buffer buffer , Semaphore init , Semaphore wait){
        super("Thread2");
        m_barrier = barrier;
        m_buffer = buffer;
        m_init = init;
        m_wait = wait;
        start();
    }

    @Override
    public void run(){
        Random rnd = new Random(System.currentTimeMillis());

        while (true){
            try
            {
                System.out.println(getName() + " waits before barrier");
                if(!Main.isP3Closed){
                    m_barrier.await();
                } else {
                    throw new BrokenBarrierException();
                }




            }catch(BrokenBarrierException e)
            {
                m_init.release();
                Main.isP2Closed = true;
                System.out.println(e.getMessage());
                e.printStackTrace();
                break;
            }
            catch(InterruptedException e)
            {
                System.out.println(e.getMessage());
                Main.isP2Closed = true;
                e.printStackTrace();
                break;
            }
            System.out.println(getName() + " works after barrier");

            System.out.println(getName() + " sent semaphore ");
            m_init.release();

            System.out.println(getName() + " wait another semaphore ");
            try{

                    m_wait.acquire();


            } catch (InterruptedException e){
                System.out.println(e.getMessage());
                Main.isP2Closed = true;
                e.printStackTrace();
            }
            System.out.println(getName() + " works after semaphore");



            int value = rnd.nextInt(100);
            m_buffer.push(value);
            System.out.println(getName() + " push value :" + value );
//            try{
//                Thread.sleep(5000);
//            } catch (InterruptedException e){
//                System.out.println(e.getMessage());
//            }
        }
    }
}
