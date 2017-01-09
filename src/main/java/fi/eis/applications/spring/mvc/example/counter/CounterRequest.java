package fi.eis.applications.spring.mvc.example.counter;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CounterRequest {
    @NotNull
    private final Integer int1;
    @NotNull
    private final Integer int2;
}
