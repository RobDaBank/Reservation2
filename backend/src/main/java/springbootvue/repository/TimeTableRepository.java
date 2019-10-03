package springbootvue.repository;
import springbootvue.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
    TimeTable findByTimetableid(Long timetableid );
}