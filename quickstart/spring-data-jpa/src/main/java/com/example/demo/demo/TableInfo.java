package com.example.demo.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="table_info",catalog = "test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TableInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int tableid;
    private String tablename;
    private int dbid;
    private String tabletext;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tableid", unique = true, nullable = false)
    public int getTableid() {
        return tableid;
    }
    
    public void setTableid(int tableid) {
        this.tableid = tableid;
    }
    
    @Column(name = "tablename", nullable = false)
    public String getTablename() {
        return tablename;
    }
    
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    
    @Column(name = "dbid")
    public int getDbid() {
        return dbid;
    }
    
    public void setDbid(int dbid) {
        this.dbid = dbid;
    }
    
    @Column(name = "tabletext")
    public String getTabletext() {
        return tabletext;
    }
    
    public void setTabletext(String tabletext) {
        this.tabletext = tabletext;
    }
}