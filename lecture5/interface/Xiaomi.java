public class Xiaomi implements Phone {
    private boolean isOn;
    private boolean isPowerOn;
    private int volume;

    // constructor
    public Xiaomi() {
        this.isOn = false;
        this.isPowerOn = false;
        this.volume = 50;
    }

    @Override
    public void powerOn() {
        isPowerOn = true;
        System.out.println("Power on");
        System.out.println("Xiaomi is on");
    }

    @Override
    public void powerOff() {
        isPowerOn = false;
        System.out.println("Power off");
        System.out.println("Xiaomi is off");
    }

    @Override
    public void volumeUp() {
        if (isPowerOn) {

            if (this.volume == MAX_VOLUME) {
                System.out.println("Volume is at max");
            } else {
                this.volume += 10;
                System.out.println("Volume is at " + this.volume);
            }
        } else {
            System.out.println("Phone is off");
        }

    }

    @Override
    public void volumeDown() {
        System.out.println("Volume down");
    }

}
