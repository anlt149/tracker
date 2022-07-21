package com.app.tracker.core.service;

import com.app.tracker.core.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl<R extends BaseRepository<T>, T> implements BaseService<T> {
    @Autowired
    protected R baseRepository;

    @Override
    public BaseRepository<T> getRepository() {
        return baseRepository;
    }
}
