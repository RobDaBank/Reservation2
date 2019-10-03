package springbootvue.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ReservationCriteraDTO {

    private Long customerId;
    private Long fieldcateId;
    private Long timeableId;
    private Date date;

}
