public class DeliveryManager {
    protected long salesAmount;
    protected SneakersInfo nikeSneakerInfo;
    protected SneakerPackageInfo packageInfo;

    public void setSalesAmount(long salesAmount) {
        this.salesAmount = salesAmount;
    }

    public void setNikeSneakerInfo(SneakersInfo nikeSneakerInfo) {
        this.nikeSneakerInfo = nikeSneakerInfo;
    }

    public void setPackageInfo(SneakerPackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public SneakerPackage makeSneakerPackage(){
        System.out.println("배송 관리자가 스니커를 포장합니다.");
        return new SneakerPackage();
    }

    public void sayPayment(long deliveryCost){
        System.out.printf("결제 해 주신 %d 원 감사합니다.\n", deliveryCost);
    }

    public void addSalesAmount(long cachePackage){
        salesAmount += cachePackage;
    }
}
