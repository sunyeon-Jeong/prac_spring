class Transport {
    private int busNum; // 버스번호
    private int busOil = 100; // 주유량 기본 100
    private int busSpeed = 0; // 속도 기본 0
    private String busState; // 운행 or 차고지행
    private int busPeople; // 승객

    Transport(int n) {
        setBusNum(n);
    }

    void setPassenger(int people) {
        busPeople += people;
    }

    public int getBusNum() {
        return busNum;
    }
    public int getBusOil() {
        return busOil;
    }
    public int getBusSpeed() {
        return busSpeed;
    }
    public String getBusState() {
        return busState;
    }
    public int getBusPeople() {
        return busPeople;
    }

    public void setBusNum(int busNum) {
        this.busNum = busNum;
    }
    public void setBusOil(int busOil) {
        this.busOil += busOil;
    }
    public void setBusSpeed(int busSpeed) {
        this.busSpeed += busSpeed;
        System.out.println("현재 속도는 " + this.busSpeed + "입니다.");
    }
    public void setBusState(String busState) {
        this.busState = busState;
    }
    public void setBusPeople(int busPeople) {
        this.busPeople = busPeople;
    }
}