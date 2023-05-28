package springwebsell.websell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springwebsell.websell.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
