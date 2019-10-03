package springbootvue.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
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

import springbootvue.dto.ReservationCriteraDTO;
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
    @Autowired
    private EmployeeRepository employeeRepository;

    public ReservationController(ReservationRepository reservationRepository, CustomerRepository customerRepository,
            FieldCategoryRepository fieldcategoryRepository, TimeTableRepository timetableRepository,EmployeeRepository employeeRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.fieldcategoryRepository = fieldcategoryRepository;
        this.timetableRepository = timetableRepository;
        this.employeeRepository = employeeRepository;
    }

    // Customer
    @GetMapping("/customer")
    public Collection<Customer> Customers() {
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/customer/{id}")
    public Optional<Customer> Customers(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer;
    }
    // Employee
    @GetMapping("/employee")
    public Collection<Employee> Employee() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    // FieldCategoty
    @GetMapping("/fieldcate")
    public Collection<FieldCategory> FieldCategories() {
        return fieldcategoryRepository.findAll().stream().collect(Collectors.toList());
    }

    // TimeTable
    @GetMapping("/timetable")
    public Collection<TimeTable> TimeTables() {
        return timetableRepository.findAll().stream().collect(Collectors.toList());
    }

    // Reservation
    @GetMapping("/reservation")
    public Collection<Reservation> Reservations() {
        return reservationRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/reservation/create")
    public Reservation newReservation(@RequestBody ReservationCriteraDTO ResCriDTo) {

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(ResCriDTo);

        long C = ResCriDTo.getCustomerId();
        long F = ResCriDTo.getFieldcateId();
        long T = ResCriDTo.getTimeableId();
        long E = ResCriDTo.getEmployeeId();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Reservation newReservation = new Reservation();
        Customer customer = customerRepository.findById(C);
        FieldCategory fieldcategory = fieldcategoryRepository.findById(F);
        TimeTable timetable = timetableRepository.findById(T);
        Employee employee = employeeRepository.findById(E);

        newReservation.setCustomer(customer);
        newReservation.setFieldcategory(fieldcategory);
        newReservation.setTimetable(timetable);
        newReservation.setEmployee(employee);
        newReservation.setDate(ResCriDTo.getDate());

        return reservationRepository.save(newReservation);
    }
}