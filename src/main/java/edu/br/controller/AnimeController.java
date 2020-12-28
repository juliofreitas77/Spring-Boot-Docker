package edu.br.controller;

import edu.br.domain.Anime;
import edu.br.requests.dto.AnimePostRequestBody;
import edu.br.requests.dto.AnimePutRequestBody;
import edu.br.service.AnimeService;
import edu.br.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {

    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> list() {
        log.info(dateUtil.formatLocalDateTimeToDataBase(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        log.info(dateUtil.formatLocalDateTimeToDataBase(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.findByIdOrThrowNewBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody AnimePostRequestBody animePostRequestBody){
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.info(dateUtil.formatLocalDateTimeToDataBase(LocalDateTime.now()));
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequestBody animePutRequestBody) {
        log.info(dateUtil.formatLocalDateTimeToDataBase(LocalDateTime.now()));
        animeService.update(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
