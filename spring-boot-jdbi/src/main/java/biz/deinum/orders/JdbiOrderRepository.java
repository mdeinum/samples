package biz.deinum.orders;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
class JdbiOrderRepository implements OrderRepository {

    private static final String INSERT_ORDER_QUERY = "INSERT INTO orders(id, amount) VALUES (:id, :amount);";
    private static final String SELECT_ORDERS_QUERY = "SELECT id, amount FROM orders";
    private static final String SELECT_ORDER_QUERY = "SELECT id, amount FROM orders WHERE id=:id";
    private final Jdbi jdbi;

    JdbiOrderRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public List<Order> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_ORDERS_QUERY)
                        .mapTo(Order.class)).list();
    }

    @Override
    public Optional<Order> findById(String id) {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_ORDER_QUERY)
                        .bind("id", id)
                        .mapTo(Order.class)).findFirst();
    }

    @Override
    public Order save(Order order) {
        jdbi.useHandle(handle ->
                handle.createUpdate(INSERT_ORDER_QUERY)
                        .bind("id", order.getId())
                        .bind("amount", order.getAmount()).execute());
        return order;
    }

    @Override
    public List<Order> saveAll(List<Order> orders) {
        jdbi.useHandle(handle -> {
            PreparedBatch preparedBatch = handle.prepareBatch(INSERT_ORDER_QUERY);
            orders.forEach(order -> preparedBatch.bind("id", order.getId()).bind("amount", order.getAmount()).add());
            preparedBatch.execute();
        });

        return orders;
    }
}
