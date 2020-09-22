package lambda;

/**
 * Created by rgrover on 29/11/18.
 */
@FunctionalInterface
public interface ICondition {
    boolean test(Person p);
}
