package springbootvue.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@Entity
@NoArgsConstructor
@Table(name="TIMETABLE")
public class TimeTable {
    @Id
    @SequenceGenerator(name="TimeTable_seq",sequenceName="TimeTable_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TimeTable_seq")
    @Column(name="TIME_ID",unique = true, nullable = true)
    private @NonNull Long timetableid;
    private @NonNull String timeString;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Reservation> reservations;
}