import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Staff {
    private Map<String, SneakersInfo> sneakersInfoMap;
    private Map<String, Long> sneakersStockMap;
    private List<SaleInfo> saleInfoList;
    private long salesAmount;
    private boolean havingNikeSneakersInStore;

    public Map<String, SneakersInfo> getSneakersInfoMap() {
        return sneakersInfoMap;
    }

    public void setSneakersInfoMap(Map<String, SneakersInfo> sneakersInfoMap) {
        this.sneakersInfoMap = sneakersInfoMap;
    }

    public Map<String, Long> getSneakersStockMap() {
        return sneakersStockMap;
    }

    public void setSneakersStockMap(Map<String, Long> sneakersStockMap) {
        this.sneakersStockMap = sneakersStockMap;
    }

    public Staff(long salesAmount) {
        this.sneakersInfoMap = new HashMap<>();
        this.sneakersStockMap = new HashMap<>();
        this.saleInfoList = new ArrayList<>();
        this.salesAmount = salesAmount;
    }

    public void readFileAndSetSneakerInfoMap(){
        try(BufferedReader fis = new BufferedReader(new FileReader("src/nike-sneaker-characters.txt"))){

            String line;
            while(true){
                line = fis.readLine();
                if(line == null) break;

                String[] strArray = line.split("\\|");
                String modelName = strArray[0];
                long price = Long.parseLong(strArray[1]);
                String[] features = strArray[2].split(",");

                SneakersInfo sneakersInfo = new SneakersInfo(modelName, price, features);
                sneakersInfoMap.put(modelName, sneakersInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("직원: 운동화 정보 다음과 같이 숙지하였습니다. \n" + this.sneakersInfoMap);
    }

    public void readFileAndSetSneakersStockMap() {
        try(BufferedReader fis = new BufferedReader(new FileReader("src/nick-sneaker-stocks.txt"))){

            String line;
            while(true){
                line = fis.readLine();
                if(line == null) break;

                String[] strArray = line.split("\\|");
                String modelName = strArray[0];
                long stock = Long.parseLong(strArray[1]);

                sneakersStockMap.put(modelName, stock);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("직원: 운동화 재고 정보 다음과 같이 숙지하였습니다." + this.sneakersStockMap);
    }

    public void setHavingNikeSneakersInStore(boolean havingNikeSneakersInStore) {
        this.havingNikeSneakersInStore = havingNikeSneakersInStore;
    }

    public boolean checkHavingNikeSneakersInStore() {
        return !sneakersStockMap.isEmpty();
    }

    public void sayPayment(long nikePrice) {
        System.out.printf("직원: 고객님 신발 주문 도와드리겠습니다. %d원 입니다\n", nikePrice);
    }

    public void addSalesAmount(long cache, Customer customer){
        salesAmount += cache;
        customer.setCache(customer.getCache()-cache);
    }

    public Sneakers offerNikeSneakers(){
        return new Sneakers();
    }

    public SneakerPackageInfo orderNikeSneakersToDeliverManager(DeliveryManager deliveryManager){
        System.out.println("직원, 배송 관리자님 나이키 스니커즈 주문(?) 부탁드립니다.");
        return deliveryManager.packageInfo;
    }

    public long calculateDeliveryCost(SneakerPackageInfo sneakerPackageInfo, Customer customer){
        long price = sneakerPackageInfo.costForDeliver;
        salesAmount += price;
        customer.setCache(customer.getCache()-price);
        return price;
    }

    public void sayNikePackageInfo(SneakerPackageInfo sneakerPackageInfo){
        System.out.printf("배송담당자: 고객님 배송은 %d 일 걸릴 예정이고 배송금액은 %d 소요되십니다.\n", sneakerPackageInfo.daysForDeliver, sneakerPackageInfo.costForDeliver);
    }

    public long returnRefund(long cache){
        salesAmount -= cache;
        return cache;
    }
}