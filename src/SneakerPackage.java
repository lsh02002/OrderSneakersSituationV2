public class SneakerPackage {
    public SneakerPackage beUnBoxed(){
        System.out.println("손님이 스니커즈 박스를 뜯어봅니다.");
        return new SneakerPackage();
    }
}
