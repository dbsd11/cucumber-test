package group.bison.cucumber.rest.controller.source;

import group.bison.cucumber.domain.source.service.SourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "/source")
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @GetMapping(path = "/list")
    public Mono<ResponseEntity> list() {
        return Mono.just(ResponseEntity.ok(sourceService.getAllSources()));
    }
}