package br.com.jurisconexao.hub.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import br.com.jurisconexao.hub.models.UriEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UriRepository extends JpaRepository<UriEntity, Long> {
    List<UriEntity> findAll();
}
