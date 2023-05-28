package springwebsell.websell.service;

import springwebsell.websell.dto.AdminDTO;
import springwebsell.websell.model.Admin;

public interface IAdminService {
    Admin save(AdminDTO adminDTO);
    Admin findByUsername(String username);
}
