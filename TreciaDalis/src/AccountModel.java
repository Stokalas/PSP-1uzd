public class AccountModel {

    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private AddressModel address;
    private String password;

    public AccountModel() { }

    public AccountModel(String name, String surname, String phoneNumber, String email,
                        AddressModel address, String password) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public AccountModel(int id, String name, String surname, String phoneNumber, String email,
                        AddressModel address, String password) {
        this(name, surname, phoneNumber, email, address, password);
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AccountModel) {
            AccountModel a = (AccountModel) obj;
            if (a.getId() == this.id) {
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Vartotojo ID negali buti neigiamas!");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
