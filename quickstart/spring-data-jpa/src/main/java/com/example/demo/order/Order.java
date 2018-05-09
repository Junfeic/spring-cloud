package com.example.demo.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="order",catalog = "test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int orderId;
    private BigDecimal amount;
    private long sellerId;
    private long userId;
    private Date date;
    private int state;
    private String memo;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId", unique = true, nullable = false)
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

    @Column
	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}

    @Column
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

    @Column
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    @Column
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

    @Column
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
    

}