package io.github.brunoalves24.bootcamp.mapper;

import io.github.brunoalves24.bootcamp.model.Stock;
import io.github.brunoalves24.bootcamp.model.dto.StockDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    public Stock toEntity(StockDto stockDto) {
        Stock stock = Stock.builder().id(stockDto.getId()).name(stockDto.getName())
                .price(stockDto.getPrice()).variation(stockDto.getVariation())
                .date(stockDto.getDate()).build();

        return stock;
    }

    public StockDto toDto(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setId(stock.getId());
        stockDto.setName(stock.getName());
        stockDto.setPrice(stockDto.getPrice());
        stockDto.setVariation(stock.getVariation());
        stockDto.setDate(stock.getDate());

        return stockDto;
    }

    public List<StockDto> toDto(List<Stock> stockList) {
        return stockList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
