package springbootvue.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import java.util.Date;

import springbootvue.entity.*;
import springbootvue.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ReservationController {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private FieldCategoryRepository fieldcategoryRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TimeTableRepository timetableRepository;

    public ReservationController(ReservationRepository reservationRepository,
                                CustomerRepository customerRepository,
                                FieldCategoryRepository fieldcategoryRepository,
                                TimeTableRepository timetableRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.fieldcategoryRepository = fieldcategoryRepository;
        this.timetableRepository = timetableRepository;
    }
    //Customer
    @GetMapping("/customer")
    public Collection<Customer> Customers() {
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/customer/{id}")
    public Optional<Customer> Customers(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer;
    }
    //FieldCategoty
    @GetMapping("/fieldcate")
    public Collection<FieldCategory> FieldCategories() {
        return fieldcategoryRepository.findAll().stream().collect(Collectors.toList());
    }
    //TimeTable
    @GetMapping("/timetable")
    public Collection<TimeTable> TimeTables() {
        return timetableRepository.findAll().stream().collect(Collectors.toList());
    }

    //Reservation
    @GetMapping("/reservation")
    public Collection<Reservation> Reservations() {
        return reservationRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/reservation/{customer_id}/{fieldcate_id}/{timeable_id}")
    public Reservation newReservation(Reservation newReservation,
    @PathVariable long customer_id,
    @PathVariable long fieldcate_id,
    // @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
    @PathVariable long timeable_id) {
   
    Customer customer = customerRepository.findById(customer_id);
    FieldCategory fieldcategory = fieldcategoryRepository.findById(fieldcate_id);
    TimeTable timetable = timetableRepository.findById(timeable_id);

        newReservation.setCustomer(customer);
        newReservation.setFieldcategory(fieldcategory);
        newReservation.setTimetable(timetable);
        newReservation.setDate(new Date());

    return reservationRepository.save(newReservation);
    }
}