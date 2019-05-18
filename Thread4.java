import java.util.Random;

public class Thread4 extends Thread {
    private Buffer m_buffer;
    private int i = 0 ;
    Thread4(Buffer buffer){
        super("Thread4");
        m_buffer = buffer;
        start();
    }

    @Override
    public void run(){
        Random rnd = new Random(System.currentTimeMillis());

        while (true){
            i++;
            if(i > 1000){
                System.out.println("P4 Closed");
                break;
            }
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
