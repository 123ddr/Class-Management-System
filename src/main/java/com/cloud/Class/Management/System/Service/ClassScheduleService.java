package com.cloud.Class.Management.System.Service;




import com.cloud.Class.Management.System.Entity.ClassSchedule;

import com.cloud.Class.Management.System.Repository.FirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ClassScheduleService {

    private static final String COLLECTION_NAME = "classSchedules";

    @Autowired
    private FirebaseRepository firebaseRepository;

    public String createSchedule(ClassSchedule schedule) throws ExecutionException, InterruptedException {
        return firebaseRepository.save(schedule, COLLECTION_NAME);
    }

    public ClassSchedule getSchedule(String id) throws ExecutionException, InterruptedException {
        return firebaseRepository.findById(id, COLLECTION_NAME, ClassSchedule.class);
    }

    public List<ClassSchedule> getAllSchedules() throws ExecutionException, InterruptedException {
        return firebaseRepository.findAll(COLLECTION_NAME, ClassSchedule.class);
    }

    public void updateSchedule(String id, ClassSchedule schedule) throws ExecutionException, InterruptedException {
        firebaseRepository.update(id, schedule, COLLECTION_NAME);
    }

    public void deleteSchedule(String id) throws ExecutionException, InterruptedException {
        firebaseRepository.delete(id, COLLECTION_NAME);
    }
}