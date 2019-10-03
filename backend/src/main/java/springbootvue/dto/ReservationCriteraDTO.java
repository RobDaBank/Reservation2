package springbootvue.dto;

import java.util.Date;
import lombok.*;

@ToString
@Getter
@Setter
public class ReservationCriteraDTO {

    private Long customerId;
    private Long fieldcateId;
    private Long timeableId;
    private Long employeeId;
    private Date date;

}
