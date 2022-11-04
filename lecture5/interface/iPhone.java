public class iPhone implements Phone {
    private boolean isOn;
    private boolean isPowerOn;
    private int volume;

    // constructor
    public iPhone() {
        this.isOn = false;
        this.isPowerOn = false;
        this.volume = 50;
    }

    // class methods
    @Override
    public void powerOn() {
        isPowerOn = true;
        System.out.println("Power on");
        System.out.println("iPhone is on");
    }

    @Override
    public void powerOff() {
        isPowerOn = false;

    }

    @Override
    public void volumeUp() {
        if (isPowerOn) {
            volume += 10;
            System.out.println("Volume is at " + volume);
        }
    }

    @Override
    public void volumeDown() {
        if (isPowerOn) {
            volume -= 10;
            System.out.println("Volume is at " + volume);
        }
    }
}
