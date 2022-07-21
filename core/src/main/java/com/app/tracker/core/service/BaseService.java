package com.app.tracker.core.service;

import com.app.tracker.core.exceptions.BaseError;
import com.app.tracker.core.exceptions.BaseException;
import com.app.tracker.core.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

public interface BaseService<T> {
    BaseRepository<T> getRepository();

    default Page<T> list(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    default T getById(Long id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new BaseException(
                        new BaseError(HttpStatus.BAD_REQUEST,
                                      "Data not found with: " + id)));
    }

    default void deleteById(Long id) {
        if (!getRepository().existsById(id)) {
            throw new BaseException(new BaseError(
                    HttpStatus.BAD_REQUEST, "No data found with id: " + id));
        }

        getRepository().deleteById(id);
    }
}
