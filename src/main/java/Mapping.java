import Service.Reader;

public class Mapping {
    public static void main(String[] args) {
        Reader reader = new Reader();
        reader.companyReader();
        reader.passInTripReader();
        reader.tripReader();
        reader.passInTripReader();
        reader.passengerAndAddressReader();
    }


}

