package springbootvue.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
@Data
@Entity
@NoArgsConstructor
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @SequenceGenerator(name="employee_seq",sequenceName="employee_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq")
    @Column(name = "EMPLOYEE_ID", unique = true, nullable = true)
    private @NonNull Long employeeid;
    private @NonNull String employeename;
    private @NonNull String idcardnumber;
    private @NonNull String address;
    private @NonNull String telephone;
    private @NonNull String banknum;
    private @NonNull String username;
    private @NonNull String password;
    @OneToMany(fetch = FetchType.EAGER)
	private Collection<Reservation> reservations;
}

