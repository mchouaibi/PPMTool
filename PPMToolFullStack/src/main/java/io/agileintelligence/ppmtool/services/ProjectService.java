package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Backlog;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.repositories.BacklogRepository;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    // References an instantiation that happened in the container. IOC: Inversion Of Control
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project) {
        String projectIdentifierUpperCase = project.getProjectIdentifier().toUpperCase();

        try {
            project.setProjectIdentifier(projectIdentifierUpperCase);

            if(project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);;
                backlog.setProjectIdentifier(projectIdentifierUpperCase);
                backlog.setProject(project);
            } else {
                project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifierUpperCase));
            }

            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + projectIdentifierUpperCase + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier((projectIdentifier));
        if(project == null) {
            throw new ProjectIdException("Project ID '" + projectIdentifier + "' does not exists");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if(project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' does not exists");
        }

        projectRepository.delete(project);
    }
}
