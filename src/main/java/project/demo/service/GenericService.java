package project.demo.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService <T extends Serializable,ID extends Number> {

    void create(T t);

    List<T> queryAll();

    T queryById(ID id);

    void modfiy(T t);

    void remove(ID id);

    void create(String sqlId, Object parameter);

    T queryOne(String sqlId,Object parameter);

    void modify(String sqlId,Object parameter);
}
