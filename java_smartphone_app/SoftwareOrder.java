public class SoftwareOrder extends Order {
    private final LicenceType licenceType;

    public SoftwareOrder(int customerId, LicenceType licenceType) {
        super(customerId);
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
