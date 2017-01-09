package fi.eis.applications.spring.mvc.example.counter;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CounterController {
    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST)
    @ApiOperation(value = "add two numbers together", response = CounterResult.class)
    public DeferredResult<CounterResult> add(@RequestBody @Valid
            CounterRequest counterRequest) {
        DeferredResult<CounterResult> result = new DeferredResult<>();
        result.setResult(counterService.count(counterRequest));
        return result;
    }
}
