package uz.pdp.g42.common.service;

import uz.pdp.g42.common.enom.FilePath;
import uz.pdp.g42.common.model.User;
import uz.pdp.g42.common.model.Wikipidiya;

import java.io.IOException;
import java.util.List;

public class WikipidiyaService implements BaseService<Wikipidiya>{


    private  final FileService fileService;

    private WikipidiyaService(FileService fileService) {
        this.fileService = fileService;
    }
    public void add(Wikipidiya wikipidiya) throws IOException {
        fileService.writeFile(wikipidiya, FilePath.WIKIPIDIYA, Wikipidiya.class);
    }
    @Override
    public List<Wikipidiya> list() throws IOException {
        return fileService.getList(FilePath.WIKIPIDIYA.getPath(), Wikipidiya.class);

    }

    @Override
<<<<<<< HEAD
    public WikipidiyaHistory get(Long id) throws IOException {
        List<WikipidiyaHistory> list = fileService.getList(FilePath.WIKIPIDIYA.getPath(), WikipidiyaHistory.class);
        WikipidiyaHistory wikipidiya1 = list.stream().filter(wikipidiya -> wikipidiya.getChatId().equals(id)).findFirst().orElse(null);
=======
    public Wikipidiya get(Long id) throws IOException {
        List<Wikipidiya> list = fileService.getList(FilePath.WIKIPIDIYA.getPath(), Wikipidiya.class);
>>>>>>> b209c023fb99149c1e2a21d2a6d465495a1d4204

        Wikipidiya wikipidiya = list.stream().filter(wikipidiya1 -> wikipidiya1.getChatId().equals(id)).findFirst().orElse(null);
        return wikipidiya;
    }

    @Override
<<<<<<< HEAD
    public List<WikipidiyaHistory> getById(Long id) throws IOException {
        List<WikipidiyaHistory> list = fileService.getList(FilePath.WIKIPIDIYA.getPath(), WikipidiyaHistory.class);

=======
    public List<Wikipidiya> getById(Long id) throws IOException {
        List<Wikipidiya> list = fileService.getList(FilePath.WIKIPIDIYA.getPath(), Wikipidiya.class);
>>>>>>> b209c023fb99149c1e2a21d2a6d465495a1d4204
        return list.stream().filter(wikipidiya -> wikipidiya.getChatId().equals(id)).toList();

    }
}
