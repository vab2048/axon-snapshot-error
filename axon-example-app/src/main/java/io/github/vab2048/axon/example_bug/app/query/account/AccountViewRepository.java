package io.github.vab2048.axon.example_bug.app.query.account;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountViewRepository extends CrudRepository<AccountView, UUID> {
}
