package IGJ.imgeokjeong.office.repository;

import IGJ.imgeokjeong.office.domain.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long> {
}
