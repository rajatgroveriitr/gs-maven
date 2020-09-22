package java9.reactive.transformation;

import java9.reactive.Employee;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class MyProcessor extends SubmissionPublisher<Freelancer> implements Flow.Processor<Employee, Freelancer> {

    private Flow.Subscription subscription;
    private Function<Employee,Freelancer> function;

    public MyProcessor(Function<Employee,Freelancer> function) {
        super();
        this.function = function;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Employee emp) {
        submit((Freelancer) function.apply(emp));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
    }
}
