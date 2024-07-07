package uz.pdp.g42.common.service;

import uz.pdp.g42.common.enom.FilePath;
import uz.pdp.g42.common.model.WikipidiyaHistory;

import java.io.IOException;
import java.util.List;

public class WikipidiyaHistoryService implements BaseService<WikipidiyaHistory>{
    private FileService<WikipidiyaHistory> fileService;

    public void add(WikipidiyaHistory wikipidiya) throws IOException {
        fileService.writeFile(wikipidiya, FilePath.WIKIPIDIYA);
    }
    @Override
    public List<WikipidiyaHistory> list() throws IOException {
        return fileService.getList(FilePath.WIKIPIDIYA.getPath());
    }


    public WikipidiyaHistory get(Long id) throws IOException {
        List<WikipidiyaHistory> list = fileService.getList(FilePath.WIKIPIDIYA.getPath());
        WikipidiyaHistory wikipidiya1 = list.stream().filter(wikipidiya -> wikipidiya.getChatId().equals(id)).findFirst().orElse(null);

        return wikipidiya1;
    }

    public List<WikipidiyaHistory> getById(Long id) throws IOException {
        List<WikipidiyaHistory> list = fileService.getList(FilePath.WIKIPIDIYA.getPath());

        return list.stream().filter(wikipidiya -> wikipidiya.getChatId().equals(id)).toList();
    }
}