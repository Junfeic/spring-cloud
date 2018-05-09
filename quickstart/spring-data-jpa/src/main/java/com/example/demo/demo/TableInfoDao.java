package com.example.demo.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TableInfoDao extends CrudRepository<TableInfo, Integer> {
    
    @Query(value="select count(*) from table_info",nativeQuery=true)
    int getcount();
}
