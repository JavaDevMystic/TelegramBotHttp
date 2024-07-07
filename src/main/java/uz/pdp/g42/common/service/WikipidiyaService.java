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
    public Wikipidiya get(Long id) throws IOException {
        List<Wikipidiya> list = fileService.getList(FilePath.WIKIPIDIYA.getPath(), Wikipidiya.class);
=======
    public WikipidiyaHistory get(Long id) throws IOException {
        List<WikipidiyaHistory> list = fileService.getList(FilePath.WIKIPIDIYA.getPath(), WikipidiyaHistory.class);
        WikipidiyaHistory wikipidiya1 = list.stream().filter(wikipidiya -> wikipidiya.getChatId().equals(id)).findFirst().orElse(null);
>>>>>>> 69cbd69fbc797d3b66ecca1f5c0903355a31abf5

        Wikipidiya wikipidiya = list.stream().filter(wikipidiya1 -> wikipidiya1.getChatId().equals(id)).findFirst().orElse(null);
        return wikipidiya;
    }

    @Override
<<<<<<< HEAD
    public List<Wikipidiya> getById(Long id) throws IOException {
        List<Wikipidiya> list = fileService.getList(FilePath.WIKIPIDIYA.getPath(), Wikipidiya.class);
=======
    public List<WikipidiyaHistory> getById(Long id) throws IOException {
        List<WikipidiyaHistory> list = fileService.getList(FilePath.WIKIPIDIYA.getPath(), WikipidiyaHistory.class);

>>>>>>> 69cbd69fbc797d3b66ecca1f5c0903355a31abf5
        return list.stream().filter(wikipidiya -> wikipidiya.getChatId().equals(id)).toList();

    }
}
