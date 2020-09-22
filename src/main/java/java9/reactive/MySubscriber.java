package java9.reactive;

import java.util.concurrent.Flow;

public class MySubscriber implements Flow.Subscriber<Employee> {

    private Flow.Subscription subscription;

    private int counter = 0;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("Subscribed");
        this.subscription = subscription;
        this.subscription.request(1); //requesting data from publisher
        System.out.println("onSubscribe requested 1 item");
    }

    @Override
    public void onNext(Employee item) {
        System.out.println("Processing Employee "+item);
        counter++;
        // Note that in this case, our logic to halt main thread before all the messages are processed will go into infinite loop.
        // We can add some additional logic for this scenario, may be some global variable to look for if subscriber has stopped processing or canceled subscription.
//        if(counter==3) {
//            System.out.println("Reached the limit, cancelling the subscription");
//            this.subscription.cancel();
//            return;
//        }
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("Some error happened");
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("All Processing Done");
    }

    public int getCounter() {
        return counter;
    }
}
