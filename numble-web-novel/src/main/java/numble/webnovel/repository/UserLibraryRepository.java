package numble.webnovel.repository;

import numble.webnovel.domain.UserLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLibraryRepository extends JpaRepository<UserLibrary, String> {
}
