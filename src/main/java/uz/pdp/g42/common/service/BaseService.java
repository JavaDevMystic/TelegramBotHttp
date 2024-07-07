package uz.pdp.g42.common.service;

import java.io.IOException;
import java.util.List;

public interface BaseService<T> {
    void add(T t) throws IOException;
    List<T> list() throws IOException;
}
