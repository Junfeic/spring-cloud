package com.example.demo.domain.payment;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    private static final long serialVersionUID = 1L;
    
    public static final int CREATED=10;
    public static final int PAYED=20;
    public static final int CANCELED=30;
    
    private int id;
    private int orderId;
    private BigDecimal amount;
    private int state;
    
    private int payChannel;
    private String seriaNumber;
    private Date payTime;

	private Date createTime;
    private Date updateTime;

	public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(int payChannel) {
		this.payChannel = payChannel;
	}

	public String getSeriaNumber() {
		return seriaNumber;
	}

	public void setSeriaNumber(String seriaNumber) {
		this.seriaNumber = seriaNumber;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
