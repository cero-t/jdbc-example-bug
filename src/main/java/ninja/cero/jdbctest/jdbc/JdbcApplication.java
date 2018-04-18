package ninja.cero.jdbctest.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@RestController
public class JdbcApplication {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

    @GetMapping
    public List<Item> findAll() {
        return jdbcTemplate.query("select * from item", (resultSet, i) -> {
            Item item = new Item();
            item.id = resultSet.getLong("id");
            item.name = resultSet.getString("name");
            item.unitPrice = resultSet.getBigDecimal("unit_price");
            return item;
        });
    }

    class Item implements Serializable {
        private static final long serialVersionUID = 1L;
        public Long id;
        public String name;
        public BigDecimal unitPrice;
    }
}
