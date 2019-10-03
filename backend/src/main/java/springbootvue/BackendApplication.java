package springbootvue;

import springbootvue.entity.*;
import springbootvue.repository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner init(CustomerRepository customerRepository,FieldCategoryRepository fieldcategoryRepository, TimeTableRepository timetableRepository, EmployeeRepository employeeRepository) {
		return args -> {
			// Customers name 
			Stream.of("ธนาธร", "ประยุทธ์", "ประวิตร", "ชวน").forEach(name -> {
				Customer customer = new Customer(); 
				customer.setName(name); 
				customerRepository.save(customer); 
			});
			// FieldType field
			Stream.of("สนามฟุตบอล", "สนามแบตมินตัน 1", "สนามแบตมินตัน 2","สนามบาสเก็ตบอล 1","สนามบาสเก็ตบอล 2", "สนามวอลเลย์บอล 1", "สนามวอลเลย์บอล 2").forEach(field -> {
				FieldCategory fieldcategory = new FieldCategory(); 
				fieldcategory.setField(field); 
				fieldcategoryRepository.save(fieldcategory); 
			});
			// TimeTable timeString
			Stream.of("08:00-09:00 น.", "09:00-10:00 น.", "10:00-11:00 น.", "11:00-12:00 น.", "12:00-13:00 น.", "13:00-14:00 น.", "14:00-15:00 น.", "15:00-16:00 น.", "16:00-17:00 น.", "17:00-18:00 น.", "18:00-19:00 น.", "19:00-20:00 น.", "20:00-21:00 น.", "21:00-22:00 น.").forEach(timeString -> {
				TimeTable timetable = new TimeTable(); 
				timetable.setTimeString(timeString); 
				timetableRepository.save(timetable); 
			});
			// Employee name
			Stream.of("ตั๊ก", "ชมพู่", "แบงค์", "ตาล", "เบน", "หนุน").forEach(name -> {
				Employee employee = new Employee(); 
				employee.setName(name); 
				employeeRepository.save(employee); 
			});

			customerRepository.findAll().forEach(System.out::println);
			fieldcategoryRepository.findAll().forEach(System.out::println); 
			timetableRepository.findAll().forEach(System.out::println); 
			employeeRepository.findAll().forEach(System.out::println);
		};
	}

}
