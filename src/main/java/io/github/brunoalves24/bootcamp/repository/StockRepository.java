package io.github.brunoalves24.bootcamp.repository;

import io.github.brunoalves24.bootcamp.model.Stock;
import io.github.brunoalves24.bootcamp.model.dto.StockDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByNameAndDate(String name, LocalDate date);

    @Query("SELECT stock FROM STOCK stock WHERE stock.name = :name AND stock.date = date AND stock.id <> :id")
    Optional<Stock> findByStockUpdate(String name, LocalDate date, Long id);

    @Query("SELECT stock FROM STOCK stock WHERE stock.date = CURRENT_DATE ")
    List<StockDto> findByToday();
}
