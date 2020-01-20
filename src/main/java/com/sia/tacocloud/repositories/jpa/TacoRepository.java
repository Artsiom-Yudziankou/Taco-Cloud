package com.sia.tacocloud.repositories.jpa;

import com.sia.tacocloud.essences.jpa.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
