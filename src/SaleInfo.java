import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaleInfo {
    private ServiceType serviceType;
    private String sneakModelName;

    private Long sneakerPrice;
    private CustomerLevel customerLevel;
    private String customerName;
    private LocalDateTime localDateTime;

    public SaleInfo(ServiceType serviceType, Customer customer, Long price) {
        this.serviceType = serviceType;
        this.sneakModelName = customer.getSneakerModel();
        this.customerLevel = customer.getCustomerLevel();
        this.customerName = customer.getCustomerName();
        this.sneakerPrice = price;
        this.localDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return serviceType + "|" + sneakModelName + "|" + sneakerPrice + "|" + customerLevel + "|" + customerName + "|" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}