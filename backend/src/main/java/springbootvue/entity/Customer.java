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
@Table(name="CUSTOMER")
public class Customer {
	@Id
	@SequenceGenerator(name="Customer_seq",sequenceName="Customer_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Customer_seq")
	@Column(name="CUSTOMER_ID",unique = true, nullable = true)
	private @NonNull Long customerid;
    private @NonNull String customername;
    private @NonNull String address;
    private @NonNull String telephone;
    private @NonNull String username;
    private @NonNull String password;
	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Reservation> reservations;
}
