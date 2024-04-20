import java.util.Arrays;

public class Customer {
    private long cache;
    private boolean likeDelivery;

    private final CustomerLevel customerLevel;
    private final String customerName;
    private final String sneakerModel;
    private final double discount = 0.1;

    public Customer(CustomerLevel customerLevel, String customerName, boolean likeDelivery, long cache, String sneakerModel) {
        this.cache = cache;
        this.likeDelivery = likeDelivery;
        this.customerLevel = customerLevel;
        this.customerName = customerName;
        this.sneakerModel = sneakerModel;
    }

    public CustomerLevel getCustomerLevel() {
        return customerLevel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getSneakerModel() {
        return sneakerModel;
    }

    public long getCache() {
        return cache;
    }

    public void setCache(long cache) {
        this.cache = cache;
    }

    public void setLikeDelivery(boolean likeDelivery) {
        this.likeDelivery = likeDelivery;
    }

    public void askNikeSneakersToStaff(Staff staff){
        System.out.println("손님: Nike 운동화에 대해서 알려주세요");
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", customerLevel=" + customerLevel +
                ", customerName='" + customerName + '\'' +
                ", likeDelivery=" + likeDelivery +
                "cache=" + cache +
                ", sneakerModel='" + sneakerModel + '\'' +
                '}'+"\n";
    }

    public long askAndGetSneakerPriceFromStaff(Staff staff, String sneakerModel){
        String[] feature = staff.getSneakersInfoMap().get(sneakerModel).getFeatures();
        long price = staff.getSneakersInfoMap().get(sneakerModel).getNikeSneakersPrice();

        System.out.printf("직원: Nike 운동화는 %s 특징과 %d 가격을 가지고 있습니다\n", Arrays.toString(feature), price);
        return price;
    }

    public boolean isAffordable(long nikePrice){
        System.out.printf("손님: 네 가격이 %d라서 마음에 드네요\n", nikePrice);
        return true;
    }

    public void sayBye(){
        System.out.println("손님: 다음에 다시올께요 안녕히 계세요.");
    }

    public void sayOrder(){
        System.out.println("손님: 네 좋네요, " + sneakerModel + " 주문 계속 진행할게요.");
    }

    public long makePayment(long nikePrice){
        System.out.printf("손님: 여기 %d원 있습니다.\n", nikePrice);
        return nikePrice;
    }

    public void sayAboutCustomer()
    {
        System.out.printf("손님: 저 %s 등급이어서 운동화 할인 %f 되는 걸로 알고 있습니다.\n", customerLevel.getKoreanName(), discount);
    }

    public void wearSneakers(SneakerPackage sneakerPackage){
        System.out.println("손님: 포장을 뜯고 스니커즈를 입어봅니다.");
    }

    public boolean isLikeDelivery(){
        return likeDelivery;
    }

    public void requireRefund(){
        System.out.println("손님: 배송료가 너무 비싸네요. 신발 가격 환불해 주세요");
    }

    public void getRefund(long refundCache){
        cache += refundCache;
    }

    public long askAndGetDeliverCostFromDeliveryManager(DeliveryManager deliveryManager){
        int price = deliveryManager.packageInfo.costForDeliver;
        System.out.printf("배송 담당자: 고객님 배송 결제 도와드리겠습니다. %d 원입니다.\n", price);
        return price;
    }
}
