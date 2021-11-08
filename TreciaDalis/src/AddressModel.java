public class AddressModel {
    private String country;
    private String city;
    private String address;

    public AddressModel(String country, String city, String address) {
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddressModel) {
            AddressModel a = (AddressModel) obj;
            if (a.getCountry().equals(this.country) && a.getCity().equals(this.city)
                    &&  a.getAddress().equals(this.address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.country + "," + this.city + "," + this.address;
    }
}
