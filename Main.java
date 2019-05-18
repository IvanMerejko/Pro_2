import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Main {


    static boolean isP6Closed = false;
    static boolean isP3Closed = false;
    static boolean isP2Closed = false;
//    static boolean isP6Closed = false;
    public static void main(String[] args){
        Buffer buffer = new Buffer();
        Integer second_value = 10;


        CyclicBarrier barrier = new CyclicBarrier(2);
        CyclicBarrier barrier2 = new CyclicBarrier(2);
        ReentrantLock mutex = new ReentrantLock();
        Semaphore sem1 = new Semaphore(0, true);
        Semaphore sem2 = new Semaphore(0, true);

        buffer.push(5);
        buffer.push(4);

        Thread1 thread1 = new Thread1(buffer);
        Thread2 thread2 = new Thread2(barrier , buffer , sem1 , sem2);
        Thread3 thread3 = new Thread3(barrier , barrier2, buffer , mutex , second_value);
        Thread4 thread4 = new Thread4(buffer);
        Thread5 thread5 = new Thread5(buffer , sem2 , sem1 , mutex , second_value);
        Thread6 thread6 = new Thread6(barrier2 , buffer , mutex , second_value);

        try{
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
        } catch (InterruptedException e){
            System.out.println("Interrupted");
        }

    }
}

