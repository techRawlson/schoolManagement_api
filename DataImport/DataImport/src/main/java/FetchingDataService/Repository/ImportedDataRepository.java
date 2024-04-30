package FetchingDataService.Repository;

import FetchingDataService.Entities.ImportedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportedDataRepository extends JpaRepository<ImportedData, Long> {
}
