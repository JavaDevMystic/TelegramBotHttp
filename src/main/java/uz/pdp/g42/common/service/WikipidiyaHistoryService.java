package uz.pdp.g42.common.service;

import uz.pdp.g42.common.enom.FilePath;
import uz.pdp.g42.common.model.WikipidiyaHistory;

import java.io.IOException;
import java.util.List;

public class WikipidiyaHistoryService implements BaseService<WikipidiyaHistory>{
    private FileService fileService;

    public void add(WikipidiyaHistory wikipidiya) throws IOException {
        fileService.writeFile(wikipidiya, FilePath.WIKIPIDIYA, WikipidiyaHistory.class);
    }
    @Override
    public List<WikipidiyaHistory> list() throws IOException {
        return fileService.getList(FilePath.WIKIPIDIYA.getPath(), WikipidiyaHistory.class);
    }

    @Override
    public WikipidiyaHistory get(Long id) throws IOException {
        List<WikipidiyaHistory> list = fileService.getList(FilePath.WIKIPIDIYA.getPath(), WikipidiyaHistory.class);
        WikipidiyaHistory wikipidiya1 = list.stream().filter(wikipidiya -> wikipidiya.getChatId().equals(id)).findFirst().orElse(null);

        return wikipidiya1;
    }

    @Override
    public List<WikipidiyaHistory> getById(Long id) throws IOException {
        List<WikipidiyaHistory> list = fileService.getList(FilePath.WIKIPIDIYA.getPath(), WikipidiyaHistory.class);

        return list.stream().filter(wikipidiya -> wikipidiya.getChatId().equals(id)).toList();
    }
}
