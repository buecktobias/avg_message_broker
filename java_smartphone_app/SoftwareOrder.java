import java.util.Date;
import java.util.UUID;

public class SoftwareOrder extends Order{
    private final LicenceType licenceType;

    public SoftwareOrder(UUID orderId, Date orderTime, int customerId, LicenceType licenceType) {
        super(orderId, orderTime, customerId);
        this.licenceType = licenceType;
    }

    public String toJSON() {
        return String.format("""
                        {
                                "BestellungsId": "%s",
                                "BestellArt": "%s",
                                "LizenzArt": "%s",
                                "Datum": "%s",
                                "KundenId": %s
                        }
                """, getOrderId(), getOrderType(), getOrderTime(), licenceType.toString(), getCustomerId());
    }
}
