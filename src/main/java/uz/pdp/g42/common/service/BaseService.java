package uz.pdp.g42.common.service;

import uz.pdp.g42.common.model.User;
import uz.pdp.g42.common.model.WikipidiyaHistory;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface BaseService<T> {
    void add(T t) throws IOException;
    List<T> list() throws IOException;

}
