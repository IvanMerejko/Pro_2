import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Buffer {

    private int[] m_list;
    private int m_size = 100;
    private int m_currentIndex = -1;
     Buffer(){
            m_list = new int[m_size + 10];
    };
     synchronized void  push(int value){
        if(m_currentIndex > m_size ){
            try{
                wait();
            } catch (InterruptedException e){
                System.out.println("Buffer Interrupted");
            }

        }

         if(m_currentIndex == -1){
             m_currentIndex = 0;
         }

         m_list[m_currentIndex] = value;
         m_currentIndex++;
         notify();

    }
     synchronized int takeFirst(){
        if(m_currentIndex < 0 ){
            try{
                wait();
            } catch (InterruptedException e){
                System.out.println("Buffer Interrupted");
            }
        }

         int value = m_list[0];

         for(int i = 1 ; i < m_currentIndex ; ++i){
             m_list[i-1] = m_list[i];
         }

             m_currentIndex--;


         notify();
         return value;

    }
     public int getSize(){
        return m_currentIndex + 1;
     }


}
