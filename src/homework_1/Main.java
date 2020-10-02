package homework_1;

import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws DummyException, InterruptedException {
        ConnectionImpl ci = new ConnectionImpl();
        ci.start();
        Session session =  ci.createSession(true);
        Destination dest = session.createDestination("<Считалка>");
        Producer producer = session.createProducer(dest);

        String[] array = {"Раз","Два", "Три"};
        Queue <String> queue = new LinkedList(Arrays.asList(array));
        for (String s:queue) {
            Thread.sleep(2000);
            producer.send(s);
        }

        session.close();
        ci.close();
    }
}
