public class PhoneUser {
  private Phone phone;
  private String name;

  // constructor
  PhoneUser(String name, Phone phone) {
    this.name = name;
    this.phone = phone;
  }

  void turnOnThePhone() {
    this.phone.powerOn();
  }

  void turnOffThePhone() {
    this.phone.powerOff();
  }

  void volumeUp() {
    this.phone.volumeUp();
  }

  void volumeDown() {
    this.phone.volumeDown();
  }

}
