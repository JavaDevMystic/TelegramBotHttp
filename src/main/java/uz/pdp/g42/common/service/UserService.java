package uz.pdp.g42.common.service;

import uz.pdp.g42.common.enom.FilePath;
import uz.pdp.g42.common.model.User;

import java.io.IOException;
import java.util.List;

public class UserService implements BaseService<User> {
    private FileService<User> fileService;

<<<<<<< HEAD
//       public UserService(FileService fileService) {
//        this.fileService = fileService;
//    }

    public void add(User user) throws IOException {
        fileService.writeFile(user,FilePath.USER,User.class);
=======
    public UserService() {
>>>>>>> 2e4b13e661dc804cc08eeb978faefc6311f232ac
    }

    public UserService(FileService<User> fileService) {
        this.fileService = fileService;
    }

    @Override
    public void add(User user) throws IOException {
        fileService.writeFile(user, FilePath.USER, User[].class);
    }

    @Override
    public List<User> list() throws IOException {
        return fileService.getList(FilePath.USER.getPath(), User[].class);
    }

    public User get(Long id) throws IOException {
        List<User> list = fileService.getList(FilePath.USER.getPath(), User[].class);
        return list.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public List<User> getById(Long id) throws IOException {
        List<User> list = fileService.getList(FilePath.USER.getPath(), User[].class);
        return list.stream().filter(user -> user.getId().equals(id)).toList();
    }
}
