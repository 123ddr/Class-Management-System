package com.cloud.Class.Management.System.Service;


import com.cloud.Class.Management.System.Entity.ClassRoom;
import com.cloud.Class.Management.System.Repository.ClassRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ClassRoomService {

    private final ClassRoomRepository repository;

    public ClassRoomService(ClassRoomRepository repository) {
        this.repository = repository;
    }

    public String createClassRoom(ClassRoom classroom) throws ExecutionException, InterruptedException {
        return repository.saveClassRoom(classroom);
    }

    public ClassRoom getClassRoom(String classroomId) throws ExecutionException, InterruptedException {
        return repository.getClassRoomById(classroomId);
    }

    public List<ClassRoom> getAllClassRooms() throws ExecutionException, InterruptedException {
        return repository.getAllClassRooms();
    }

    public String updateClassRoom(ClassRoom classroom) throws ExecutionException, InterruptedException {
        return repository.updateClassRoom(classroom);
    }

    public String deleteClassRoom(String classroomId) throws ExecutionException, InterruptedException {
        return repository.deleteClassRoom(classroomId);
    }
}
