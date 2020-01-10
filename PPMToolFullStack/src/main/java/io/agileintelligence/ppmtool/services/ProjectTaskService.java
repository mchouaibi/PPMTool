package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Backlog;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.repositories.BacklogRepository;
import io.agileintelligence.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(ProjectTask projectTask, String projectIdentifier) {
        Backlog backlog = backlogRepository.findByProjectIdentifier((projectIdentifier));
        projectTask.setBacklog(backlog);
        Integer backLogSequence = backlog.getPTSequence();
        backLogSequence++;
        projectTask.setProjectSequence(projectIdentifier + "-" + backLogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        if(projectTask.getPriority() == null) {
            projectTask.setPriority(3);
        }

        if(projectTask.getStatus() == "" || projectTask.getStatus() == null)
            projectTask.setStatus("TO_DO");

        return projectTaskRepository.save(projectTask);
    }
}
