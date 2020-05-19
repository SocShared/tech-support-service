package ml.socshared.service.support.domain.object;

import java.util.List;

public class Page <T> {
    Integer size;
    Integer page;
    List<T> data;
}
