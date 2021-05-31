package com.excel.reader.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;



@Repository
public class DatabaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void fetchActor( ) {
        Integer res = jdbcTemplate.queryForObject("select count(*) from sector_type", Integer.class);
        System.out.println(res);

        //jdbcTemplate.execute(X);
    }
}
