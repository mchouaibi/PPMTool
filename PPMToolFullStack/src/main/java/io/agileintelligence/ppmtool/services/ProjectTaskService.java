package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Backlog;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.exceptions.ProjectNotFoundException;
import io.agileintelligence.ppmtool.repositories.BacklogRepository;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
import io.agileintelligence.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(ProjectTask projectTask, String projectIdentifier) {
        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier((projectIdentifier));
            System.out.println("*********************" + backlog);
            projectTask.setBacklog(backlog);
            Integer backLogSequence = backlog.getPTSequence();
            backLogSequence++;
            backlog.setPTSequence(backLogSequence);
            projectTask.setProjectSequence(projectIdentifier + "-" + backLogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            if(projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }

            if(projectTask.getStatus().equals("") || projectTask.getStatus() == null)
                projectTask.setStatus("TO_DO");

            return projectTaskRepository.save(projectTask);
        } catch (Exception exception) {
            throw new ProjectNotFoundException("Project is Not Found!");
        }
    }

    public Iterable<ProjectTask> findBacklogById(String id) {
        //Check if project exists
        Project project = projectRepository.findByProjectIdentifier(id);
        if(project == null) {
            throw new ProjectNotFoundException("Project '" + id + "' does not exist");
        }
        // return result
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findByProjectSequence(String backlogId, String projectTaskId) {
        // Check if backlog exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlogId);
        if(backlog == null) {
            throw new ProjectNotFoundException("Project '" + backlogId + "' does not exist");
        }

        // Check if task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectTaskId + "-" + backlogId);
        if(projectTask == null) {
            throw new ProjectNotFoundException("Task '" + projectTaskId + "' does not exist");
        }

        if(!projectTask.getProjectIdentifier().equals((backlogId))) {
            throw new ProjectNotFoundException("Task '" + projectTaskId + "' does not exist in project '" + backlogId + "'");
        }
        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId) {
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(updatedTask.getProjectSequence());
        projectTask = updatedTask;
        return projectTaskRepository.save(projectTask);
    }
}
