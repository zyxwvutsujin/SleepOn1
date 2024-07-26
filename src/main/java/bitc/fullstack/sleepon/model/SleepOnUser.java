package bitc.fullstack.sleepon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Entity
public class SleepOnUser {

    @Id
    @Column(length = 45, nullable = false)
    private String id;

    @Column(length = 45, nullable = false)
    private String pass;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 45, nullable = false)
    private String age;

    @Column(length = 45)
    private String tel;

    @Column(length = 1, nullable = false)
    private String manager = "N";

    @OneToMany(mappedBy = "user")
    private List<UserReservation> reservations;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<UserReservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<UserReservation> reservations) {
        this.reservations = reservations;
    }

    public boolean isUnderage() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(this.age, formatter);
        return Period.between(birthDate, LocalDate.now()).getYears() < 18;
    }

    public boolean isManager() {
        return this.id.startsWith("SleepOn1");
    }
}
