public class Thread1 extends Thread{
        private Buffer m_buffer;
        private int i = 0;
        Thread1(Buffer buffer){
            super("Thread1");
            m_buffer = buffer;
            start();
        }

        @Override
        public void run(){
            while (true){
                ++i;
                if(i > 1000){
                    break;
                }
                System.out.println(getName() + " TAKEN value :" + m_buffer.takeFirst() );
//                try{
//                    Thread.sleep(5000);
//                } catch (InterruptedException e){
//                    System.out.println(e.getMessage());
//                }
            }
        }
}
