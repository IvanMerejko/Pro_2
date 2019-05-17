public class Thread1 extends Thread{
        private Buffer m_buffer;
        Thread1(Buffer buffer){
            super("Thread1");
            m_buffer = buffer;
            start();
        }

        @Override
        public void run(){
            while (true){
                System.out.println(getName() + " TAKEN value :" + m_buffer.takeFirst() );
                System.out.println(getName() + " wait another semaphore ");
                try{
                    Thread.sleep(5000);
                } catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
}
