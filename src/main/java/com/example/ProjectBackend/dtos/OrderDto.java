package com.example.ProjectBackend.dtos;

import java.util.Date;

public class OrderDto {
    private Long id;
    private Date orderDate;
    private Integer total;

    public OrderDto() {
    }

    public OrderDto(Long id, Date orderDate, Integer total) {
        this.id = id;
        this.orderDate = orderDate;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
