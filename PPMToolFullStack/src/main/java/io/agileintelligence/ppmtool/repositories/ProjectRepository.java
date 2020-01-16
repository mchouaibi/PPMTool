package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.domain.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String projectIdentifier);

    @Override
    Iterable<Project> findAll();
    @Query(value = "Select * from Project where id = :id", nativeQuery = true)
    Iterable<Project> findAllById(String projectId);
/*
    @Query("select p from Project where id = :id")
    Iterable<Project> findAllById(String id);*/
}
