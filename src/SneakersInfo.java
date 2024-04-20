import java.util.Arrays;

public class SneakersInfo {
    private String modelName;

     private long nikeSneakersPrice;
    private String[] features;

    public long getNikeSneakersPrice() {
        return nikeSneakersPrice;
    }

    public String[] getFeatures() {
        return features;
    }

    public SneakersInfo(String modelName, long nikeSneakersPrice, String[] features){
        this.modelName = modelName;
        this.nikeSneakersPrice = nikeSneakersPrice;
        this.features = features;
    }

    @Override
    public String toString() {
        return "SneakersInfo[" +
                "모델이름='" + modelName + '\'' +
                ", 가격=" + nikeSneakersPrice +
                ", feature=" + Arrays.toString(features) +
                ']'+"\n";
    }
}
