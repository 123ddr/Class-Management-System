package com.cloud.Class.Management.System.Service;


import com.cloud.Class.Management.System.Entity.ClassSchedule;
import com.cloud.Class.Management.System.Repository.ClassScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ClassScheduleService {

    private final ClassScheduleRepository repository;

    public ClassScheduleService(ClassScheduleRepository repository) {
        this.repository = repository;
    }

    public String createClassSchedule(ClassSchedule schedule) throws ExecutionException, InterruptedException {
        return repository.saveClassSchedule(schedule);
    }

    public ClassSchedule getClassSchedule(String scheduleId) throws ExecutionException, InterruptedException {
        return repository.getClassScheduleById(scheduleId);
    }

    public List<ClassSchedule> getAllClassSchedules() throws ExecutionException, InterruptedException {
        return repository.getAllClassSchedules();
    }

    public String updateClassSchedule(ClassSchedule schedule) throws ExecutionException, InterruptedException {
        return repository.updateClassSchedule(schedule);
    }

    public String deleteClassSchedule(String scheduleId) throws ExecutionException, InterruptedException {
        return repository.deleteClassSchedule(scheduleId);
    }
}
