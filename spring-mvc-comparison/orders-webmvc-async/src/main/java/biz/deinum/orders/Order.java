package biz.deinum.orders;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.data.annotation.Id;

public class Order {

    @Id
    private String id;
    private String number;
    private BigDecimal amount;

    public Order() {}

    public Order(BigDecimal amount) {
        this.number=number;
        this.amount=amount;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Order [id=%s, number=%s, amount=%s]", this.id, this.number, this.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Order order = (Order) o;
        return Objects.equals(number, order.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
