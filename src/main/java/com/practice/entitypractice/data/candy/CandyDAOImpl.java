package com.practice.entitypractice.data.candy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CandyDAOImpl implements CandyDAO {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_CANDIES = "SELECT * FROM candy";
    private static final String INSERT_DELIVERY =
            "INSERT INTO candy_delivery (candy_id, delivery_id) " +
            "VALUES(:candy_id, :delivery_id)";
    private static final String SELECT_ALL_CANDIES_BY_DELIVERY =
            "SELECT c.* FROM candy_delivery AS cd JOIN candy AS c " +
                    "ON c.id = cd.candy_id WHERE cd.delivery_id = :delivery_id";
    private static final BeanPropertyRowMapper<Candy> candyRowMapper =
            new BeanPropertyRowMapper<>(Candy.class);

    @Override
    public List<Candy> findAll() {
        return jdbcTemplate.query(SELECT_ALL_CANDIES, candyRowMapper);
    }

    @Override
    public void addToDelivery(Long candyId, Long deliveryId) {
      jdbcTemplate.update(INSERT_DELIVERY,
               new MapSqlParameterSource()
                       .addValue("candy_id", candyId)
                       .addValue("delivery_id", deliveryId));
    }

    @Override
    public List<Candy> findByDelivery(Long deliveryId) {
        return jdbcTemplate.query(SELECT_ALL_CANDIES_BY_DELIVERY,
                new MapSqlParameterSource()
                        .addValue("delivery_id", deliveryId),
                candyRowMapper);
    }
}
