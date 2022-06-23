package Topics;

import java.util.Optional;

public class OptionalPractice implements Runnable {
    @Override
    public void run() {
        Optional<String> s = Optional.of("Hello optionals");

        @SuppressWarnings("ConstantConditions")
        Optional<String> s2 = Optional.ofNullable(null);
        System.out.println(s.get());
        System.out.println(Optional.empty());

        // The 13 code lines below here show a very simple example.
//        //noinspection ConstantConditions
//        if (s2.isPresent()) {
//            System.out.println("s2 is not null");
//        } else {
//            System.out.println("An Optional that contains null is equivalent to Optional.empty");
//            System.out.println("'" + s2 + "'" + " is the current value of s2");
//            s2 = Optional.of("s2 is no longer null");
//
//            //noinspection OptionalIsPresent,ConstantConditions
//            if (s2.isPresent()) {
//                System.out.println(s2.get());
//            }
//        }
        System.out.println(s2.orElse("s2 is null"));
    }
}
