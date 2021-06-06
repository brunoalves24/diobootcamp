package io.github.brunoalves24.bootcamp.controller;

import io.github.brunoalves24.bootcamp.model.dto.StockDto;
import io.github.brunoalves24.bootcamp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/stock")
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDto> save(@Valid @RequestBody StockDto stockDto) {
        return ResponseEntity.ok(stockService.save(stockDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDto> update(@RequestBody StockDto stockDto) {
        return ResponseEntity.ok(stockService.update(stockDto));
    }

    @GetMapping
    public ResponseEntity<List<StockDto>> findAll() {
        List<StockDto> stockDtoList = new ArrayList<>();
        StockDto stockDto = new StockDto();
        stockDtoList.add(stockDto);

        return ResponseEntity.ok(stockDtoList);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(stockService.findById(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.delete(id));
    }

    @GetMapping(value = "/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDto>> findByToday() {
        return ResponseEntity.ok(stockService.findByToday());
    }

}
