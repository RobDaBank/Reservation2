package springbootvue.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
@Data
@Entity
@NoArgsConstructor

@Table(name="FIELDCATEGORY")
public class FieldCategory {
    @Id
    @SequenceGenerator(name="FieldCategory_seq",sequenceName="FieldCategory_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FieldCategory_seq")  
    @Column(name = "FIELDCATEGORY_ID", unique = true, nullable = true)
    private @NonNull Long fieldid;
    private @NonNull String field;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Reservation> reservations;
}
