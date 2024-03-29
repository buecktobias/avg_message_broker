import java.util.Date;
import java.util.UUID;

public abstract class Order {
    private final UUID orderId;
    private final Date orderTime;
    private final int customerId;

    public Order(int customerId) {
        this.orderId = UUID.randomUUID();
        this.orderTime = new Date();
        this.customerId = customerId;
    }

    protected String getOrderType() {
        if (this instanceof HardwareOrder) {
            return "HardwareOrder";
        } else if (this instanceof SoftwareOrder) {
            return "SoftwareOrder";
        } else {
            throw new RuntimeException("Unknown Order!");
        }
    }

    public abstract String toJSON();

    protected UUID getOrderId() {
        return orderId;
    }

    protected Date getOrderTime() {
        return orderTime;
    }

    protected int getCustomerId() {
        return customerId;
    }
}
