package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
// Set base mapping to api/project
@RequestMapping("api/project")
// Allows requests from a specific origin
@CrossOrigin
public class ProjectController {

    /*

    The @Autowired annotation spares you the need to do the wiring by yourself in the XML file (or any other way) and just finds for you what needs to be injected where, and does that for you.

    This tag will do an auto-scanning. Assuming each class that has to become a bean is annotated with a correct annotation like @Component (for simple bean) or @Controller (for a servlet control) or @Repository (for DAO classes)       and these classes are somewhere under the package io.agileintelligence.ppmtool, Spring will find all of these and create a bean for each one. This is done in 2 scans of the classes - the first time it just searches for           classes that need to become a bean and maps the injections it needs to be doing, and on the second scan it injects the beans.

     */

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if(errorMap != null)
            return errorMap;
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    // Appends the projectId to the end of the link and specifies that it's a GET request
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectId(@PathVariable String projectId) {
        Project project = projectService.findProjectByIdentifier(projectId.toUpperCase());
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId.toUpperCase());
        return new ResponseEntity<String>("Project '" + projectId + "' was deleted!", HttpStatus.OK);
    }
    
}
