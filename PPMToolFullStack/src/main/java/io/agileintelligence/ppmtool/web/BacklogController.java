package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
    @Autowired
    private ProjectTaskService projectTaskService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlogId}")
    public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String backlogId) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if(errorMap != null) {
            return errorMap;
        }
        ProjectTask projectTask1 = projectTaskService.addProjectTask(projectTask, backlogId);
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlogId}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlogId) {
        return projectTaskService.findBacklogById(backlogId);
    }

    @GetMapping("/{backlogId}/{projectTaskId}")
    public ResponseEntity<?> getByProjectSequence(@PathVariable String backlogId, @PathVariable String projectTaskId) {
        ProjectTask projectTask = projectTaskService.findByProjectSequence(backlogId, projectTaskId);
        return new ResponseEntity<ProjectTask>( projectTask, HttpStatus.OK);    }

    @PatchMapping("/{backlogId}/{projectTaskId}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String backlogId, @PathVariable String projectTaskId) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if(errorMap != null)
            return errorMap;

        ProjectTask updatedTask = projectTaskService.updateByProjectSequence(projectTask, backlogId, projectTaskId);
        return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);
    }

}
