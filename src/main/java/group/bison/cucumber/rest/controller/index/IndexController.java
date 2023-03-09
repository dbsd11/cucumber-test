package group.bison.cucumber.rest.controller.index;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public Mono<ResponseEntity> index() {
        return Mono.just(ResponseEntity.ok("hello, welcome to use cucumber test service"));
    }
}