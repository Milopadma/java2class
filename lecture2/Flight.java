package lecture2;

public class Flight {
    // class variables
    private String AirlineName;
    private String flightNumber;
    private String originCity; // cities as in multiple or singular ???
    private String destinationCity;

    // class methods
    // constructor
    public Flight(String AirlineName, String flightNumber, String originCity, String destinationCity) {
        this.AirlineName = AirlineName;
        this.flightNumber = flightNumber;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
    }

    // getters
    public String getAirlineName() {
        return AirlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOriginCity() {
        return originCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    // setters
    public void setAirlineName(String airlineName) {
        AirlineName = airlineName;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    // toString
    public String toString() {
        return "Flight [Airline = " + AirlineName + ", Flight Number = " + flightNumber + ", Origin = " + originCity
                + ", Destination = " + destinationCity + "]";
    }
}

// FlightTest class

class FlightTest {
    public static void main(String[] args) {
        // create a flight
        Flight f1 = new Flight("Garuda Air", "GA787", "Denpasar", "Singapore");
        Flight f2 = new Flight("Lion Air", "LA899", "Jakarta", "Labuan Bajo");
        Flight f3 = new Flight("Citilink", "CL998", "Denpasar", "Malaysia");
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
    }
}