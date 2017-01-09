package fi.eis.applications.spring.mvc.example.counter;

import org.springframework.stereotype.Service;

@Service
public class CounterService {
    public CounterResult count(CounterRequest counterRequest) {
        return new CounterResult(
                counterRequest.getInt1() + counterRequest.getInt2());
    }
}
