import java.util.Date;
import java.util.UUID;

public class HardwareOrder extends Order{
    private final int orderAmount;
    private final int articleId;

    public HardwareOrder(int customerId, int orderAmount, int articleId) {
        super(customerId);
        this.orderAmount = orderAmount;
        this.articleId = articleId;
    }

    public String toJSON() {
        return String.format("""
                        {
                                "BestellungsId": "%s",
                                "BestellArt": "%s",
                                "Datum": "%s",
                                "Stückzahl": %s,
                                "ArtikelId": %s,
                                "KundenId": %s
                        }
                """, getOrderId(), getOrderType(), getOrderTime(), orderAmount, articleId, getCustomerId());
    }
}
