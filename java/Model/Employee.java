package Model;

/**
 * Created by penai on 31/10/2016.
 */

public class Employee {

    private int id;
    private String name;
    private String address;
    private String city;
    private long telephone;
    private String role;
    private String email;

    public Employee(String name, String address, String city, long telephone, String role, String email) {

        this.name = name;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.role = role;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}



