package uz.pdp.g42.common.service;

import lombok.NoArgsConstructor;
import uz.pdp.g42.common.enom.FilePath;
import uz.pdp.g42.common.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService implements BaseService<User> {
    //    private final User user;
    private final FileService<User> fileService;

    public UserService(FileService fileService) {
        this.fileService = fileService;
    }

    public void add(User user) throws IOException {
        fileService.writeFile(user,FilePath.USER.getPath());
    }

    public List<User> list() throws IOException {
        return fileService.getList(FilePath.USER.getPath());
    }

    public User get(Long id) throws IOException {
        List<User> list = fileService.getList(FilePath.USER.getPath());
        for (User user : list) {
            if (user.equals(id)) {
                return Optional.of(user).get();
            }
        }
        return Optional.<User>empty().get();
    }

    public List<User> getById(Long id) throws IOException {
        List<User> list = fileService.getList(FilePath.USER.getPath());
        return list.stream().filter(user -> user.getId().equals(id)).toList();
    }


}