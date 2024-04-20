import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderSneakersSituationV2 {
    public static void main(String[] args) {

        List<Customer> customers = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        final long TODAY_START_SALES_AMOUNT = 0;

        Staff staff = new Staff(TODAY_START_SALES_AMOUNT);

        staff.readFileAndSetSneakerInfoMap();
        staff.readFileAndSetSneakersStockMap();


        // 고객 대기 등록을 받습니다.
        System.out.println("안녕하세요~, \"고객등급,이름,배송선호 여부,예산,운동화 모델명\" 입력해주세요");

        //***매번 고객 데이터를 입력하기 어려워서 파일에서 넣었습니다***
        try(BufferedReader fis = new BufferedReader(new FileReader("src/customer-inputs.txt"))) {
                while (true) {
                    String response = fis.readLine();
                    String[] responseArray = response.split(",");

                    if (response.equals("끝")) {
                        break;
                    }

                    CustomerLevel customerLevel = CustomerLevel.valueOf(responseArray[0]);
                    String customerName = responseArray[1];
                    boolean isCustomerLikeDelivery = Boolean.parseBoolean(responseArray[2]);
                    Long cache = Long.parseLong(responseArray[3]);
                    String sneakerModel = responseArray[4];

                    /* 고객 대기 목록 명단에 고객 객체 넣어야합니다.*/

                    Customer customer = new Customer(customerLevel, customerName, isCustomerLikeDelivery, cache, sneakerModel);
                    customers.add(customer);;
                }
            }
        catch (IOException e) {
          e.printStackTrace();
        }

        /**
         * 이후 작업 이어서 진행 해주세요.
         */
        //고객 대기 리스트 등록 완료되었습니다.
        System.out.println("고객 대기 리스트 등록 완료되었습니다.\n" + customers);

        for(Customer customer : customers){
            boolean havingNikeSneakersInStore = staff.checkHavingNikeSneakersInStore();
            staff.setHavingNikeSneakersInStore(havingNikeSneakersInStore);

            DeliveryManager deliveryManager = new DeliveryManager();
            deliveryManager.setSalesAmount(100_000);

            SneakersInfo nikeSneakerInfo = staff.getSneakersInfoMap().get(customer.getSneakerModel());
            deliveryManager.setNikeSneakerInfo(nikeSneakerInfo);
            //deliveryManager.setPackageInfo(sneakerPackageInfo);

            // 여기서 로직
            customer.askNikeSneakersToStaff(staff);
            long nikePrice = customer.askAndGetSneakerPriceFromStaff(staff, customer.getSneakerModel());

            if (!customer.isAffordable(nikePrice)) {
                customer.sayBye();
                continue;
            }

            if ( staff.checkHavingNikeSneakersInStore() ){
                customer.sayOrder();
                staff.sayPayment(nikePrice);
                long cache = customer.makePayment(nikePrice);
                staff.addSalesAmount(cache, customer);

                staff.offerNikeSneakers();
                customer.sayAboutCustomer();
                continue;
            }

            if ( !customer.isLikeDelivery() ) {
                customer.sayBye();
                continue;
            }

            customer.sayOrder();
            staff.sayPayment(nikePrice);
            long cache = customer.makePayment(nikePrice);
            staff.addSalesAmount(cache, customer);

            SneakerPackageInfo nikeSneakerPackageInfo = staff.orderNikeSneakersToDeliverManager(deliveryManager);
            staff.sayNikePackageInfo(nikeSneakerPackageInfo);
            long deliverCost = staff.calculateDeliveryCost(nikeSneakerPackageInfo, customer);

            if (customer.isAffordable(deliverCost)){
                customer.requireRefund();
                long refundCache = staff.returnRefund(cache);
                customer.getRefund(refundCache);
                customer.sayBye();
                continue;
            }
            customer.sayOrder();
            SneakerPackage sneakerPackage = deliveryManager.makeSneakerPackage();
            long deliveryCost = customer.askAndGetDeliverCostFromDeliveryManager(deliveryManager);
            deliveryManager.sayPayment(deliveryCost);

            long cachePackage = customer.makePayment(deliveryCost);
            deliveryManager.addSalesAmount(cachePackage);

            customer.wearSneakers(sneakerPackage.beUnBoxed());
        }
    }
}
