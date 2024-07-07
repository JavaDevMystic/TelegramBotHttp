package uz.pdp.g42.common.service;

import uz.pdp.g42.common.enom.FilePath;
import uz.pdp.g42.common.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService implements BaseService<User> {
//    private final User user;
      private final FileService fileService;

    public UserService(FileService fileService) {
        this.fileService = fileService;
    }

    public void add(User user) throws IOException {
        fileService.writeFile(user,FilePath.USER,User.class);
    }
    @Override
    public List<User> list() throws IOException {
        return fileService.getList(FilePath.USER.getPath(),User.class);
    }

    @Override
    public User get(UUID id) throws IOException {
        List<User> list = fileService.getList(FilePath.USER.getPath(), User.class);
        for (User user : list) {
            if (user.equals(id)) {
                return Optional.of(user).get();
            }
        }
        return Optional.<User>empty().get();
    }

    @Override
    public List<User> getById(UUID id) throws IOException {
        List<User> list = fileService.getList(FilePath.USER.getPath(), User.class);
        return list.stream().filter(user -> user.getId().equals(id)).toList();
    }


}
