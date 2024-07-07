package uz.pdp.g42.common.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface BaseService<T> {
    List<T> list() throws IOException;
    T get(UUID id) throws IOException;
    List<T> getById(UUID id) throws IOException;
}