package dz.corepulse.projectflow.Configs;

import dz.corepulse.projectflow.Models.Entities.*;
import dz.corepulse.projectflow.Models.Enums.StoryStatus;
import dz.corepulse.projectflow.Models.Enums.SubTaskStatus;
import dz.corepulse.projectflow.Models.Enums.TaskStatus;
import dz.corepulse.projectflow.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class DevDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final EpicRepository epicRepository;
    private final SprintRepository sprintRepository;
    private final StoryRepository storyRepository;
    private final TaskRepository taskRepository;
    private final SubTaskRepository subTaskRepository;

    @Override
    public void run(String... args) {
        if (projectRepository.count() > 0) {
            return;
        }

        List<User> users = createUsers();
        Project project = createProject(users);
        List<Epic> epics = createEpics(project);
        List<Sprint> sprints = createSprints(project);
        List<Story> stories = createStories(epics, sprints);
        createTasksWithSubTasks(stories, users);
    }

    private List<User> createUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            User user = new User();
            user.setName("User " + i);
            users.add(user);
        }
        return userRepository.saveAll(users);
    }

    private Project createProject(List<User> users) {
        Project project = new Project();
        project.setProjectName("Corepulse Platform");
        project.setDescription("Development backlog for the Corepulse platform");
        project.setStatut("ACTIVE");
        project.setProgress(0);
        project.setDateDebut(LocalDateTime.now().minusDays(7));
        project.setDateFin(LocalDateTime.now().plusMonths(1));
        project.getUserList().add(users.get(0));
        return projectRepository.save(project);
    }

    private List<Epic> createEpics(Project project) {
        List<Epic> epics = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            Epic epic = new Epic();
            epic.setEpicName("Epic " + i);
            epic.setDescription("Key capability " + i);
            epic.setProject(project);
            epic.setStatut("PLANNED");
            epics.add(epic);
        }
        return epicRepository.saveAll(epics);
    }

    private List<Sprint> createSprints(Project project) {
        List<Sprint> sprints = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Sprint sprint = new Sprint();
            sprint.setSprintName("Sprint " + (i + 1));
            sprint.setDescription("Iteration " + (i + 1));
            sprint.setProject(project);
            sprint.setStatut("OPEN");
            sprint.setDateDebut(LocalDateTime.now().plusDays(i * 14L));
            sprint.setDateFin(LocalDateTime.now().plusDays((i + 1L) * 14));
            sprints.add(sprint);
        }
        return sprintRepository.saveAll(sprints);
    }

    private List<Story> createStories(List<Epic> epics, List<Sprint> sprints) {
        List<Story> stories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Story story = new Story();
            story.setStoryName("Story " + (i + 1));
            story.setDescription("Generated story " + (i + 1));
            story.setEpic(epics.get(i % epics.size()));
            story.setSprint(sprints.get(i % sprints.size()));
            story.setStatut(StoryStatus.TODO);
            story.setPriority("MEDIUM");
            story.setPts(5);
            stories.add(story);
        }
        return storyRepository.saveAll(stories);
    }

    private void createTasksWithSubTasks(List<Story> stories, List<User> users) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Story story = stories.get(i % stories.size());
            Task task = new Task();
            task.setTaskName("Task " + (i + 1));
            task.setDescription("Detail task " + (i + 1));
            task.setStory(story);
            task.setSprint(story.getSprint());
            task.setPriority("HIGH");
            task.setStatut(TaskStatus.TODO);
            task.setAssignedUser(users.get(i % users.size()));
            tasks.add(task);
        }
        taskRepository.saveAll(tasks);

        List<SubTask> subtasks = new ArrayList<>();
        tasks.forEach(task -> {
            for (int i = 1; i <= 2; i++) {
                SubTask subTask = new SubTask();
                subTask.setName(task.getTaskName() + " - Subtask " + i);
                subTask.setDescription("Work package " + i);
                subTask.setTask(task);
                subTask.setStatut(SubTaskStatus.TODO);
                subtasks.add(subTask);
            }
        });
        subTaskRepository.saveAll(subtasks);
    }
}
