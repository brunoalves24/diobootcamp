package io.github.brunoalves24.bootcamp.service;

import io.github.brunoalves24.bootcamp.exception.BusinessException;
import io.github.brunoalves24.bootcamp.exception.NotFoundException;
import io.github.brunoalves24.bootcamp.mapper.StockMapper;
import io.github.brunoalves24.bootcamp.model.Stock;
import io.github.brunoalves24.bootcamp.model.dto.StockDto;
import io.github.brunoalves24.bootcamp.repository.StockRepository;
import io.github.brunoalves24.bootcamp.util.MessageUtils;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    @Autowired
    public StockService(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @Transactional
    public StockDto save(StockDto stockDto) {
        Optional<Stock> optionalStock = stockRepository.findByNameAndDate(stockDto.getName(), stockDto.getDate());

        if(optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        Stock stock = stockMapper.toEntity(stockDto);
        stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }

    @Transactional
    public StockDto update(StockDto stockDto) {
        Optional<Stock> optionalStock = stockRepository.findByStockUpdate(stockDto.getName(), stockDto.getDate(), stockDto.getId());

        if(optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        Stock stock = stockMapper.toEntity(stockDto);
        stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }

    @Transactional(readOnly = true)
    public List<StockDto>  findAll() {
        List<Stock>  list = stockRepository.findAll();

        return stockMapper.toDto(list);
    }

    @Transactional(readOnly = true)
    public StockDto findById(Long id) {
        return stockRepository.findById(id).map(stockMapper::toDto)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public StockDto delete(Long id) {
        StockDto dto = findById(id);
        stockRepository.deleteById(dto.getId());
        return dto;
    }

    @Transactional(readOnly = true)
    public List<StockDto> findByToday() {
        return stockRepository.findByToday();
    }
}
