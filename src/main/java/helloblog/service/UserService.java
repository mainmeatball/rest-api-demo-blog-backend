package helloblog.service;

import helloblog.dto.RolesDto;
import helloblog.dto.UserDto;
import helloblog.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll(int pageNo, int pageSize, String sortBy, String dir);

    User save(UserDto userDto);

    User findByUsername(String username);

    User findById(int userId);

    void deleteById(int userId);

    User update(User user, UserDto userDto);

    User updateRoles(User user, RolesDto rolesDto);

    User block(User user);

    User unblock(User user);
}
