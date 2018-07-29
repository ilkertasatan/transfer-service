package com.ingenico.epayments.transferservice.repository;

import com.ingenico.epayments.transferservice.domain.Identifiable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class InMemoryRepository<T extends Identifiable> {

    private IdGenerator idGenerator = new IdGenerator();
    private List<T> elements = Collections.synchronizedList(new ArrayList<>());

    public T create (T element) {
        elements.add(element);
        element.setId(idGenerator.getNextId());

        return element;
    }

    public List<T> findAll() {
        return elements;
    }

    public Optional<T> findById(Long id) {
        return elements.stream().filter(e -> e.getId().equals(id)).findFirst();
    }
}
