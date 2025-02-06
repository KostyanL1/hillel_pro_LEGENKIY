package org.lesson12.app;

import java.util.concurrent.atomic.AtomicInteger;

public class DataHandler {


    String[] fruits = new DataRepository().getData();

    public void getOutput() {
        // критичний блок коду
        synchronized (this) {
            StringBuilder sb = new StringBuilder();
            AtomicInteger count = new AtomicInteger(1);
            for (String fruit : fruits) {

                sb.append(String.format("(%d) %s ", count.get(), fruit));
                count.incrementAndGet();
            }
            System.out.println(Thread.currentThread().getName() + ": " + sb);
        }
    }

}
