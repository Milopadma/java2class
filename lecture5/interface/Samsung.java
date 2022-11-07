public class Samsung {
    // class variables
    private boolean isOn;
    private boolean isPowerOn;

    // constructor
    public Samsung() {
        this.isOn = false;
        this.isPowerOn = false;
    }

    // class methods
    public void powerOn() {
        isPowerOn = true;
        System.out.println("Power on");
        System.out.println("Samsung is on");
    }

    public void powerOff() {
        isPowerOn = false;
    }

    public void volumeUp() {
        if (isPowerOn) {
            System.out.println("Volume up");
        }
    }
}
