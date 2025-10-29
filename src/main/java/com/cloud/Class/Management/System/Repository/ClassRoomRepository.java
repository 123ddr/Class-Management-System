package com.cloud.Class.Management.System.Repository;


import com.cloud.Class.Management.System.Entity.ClassRoom;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ClassRoomRepository {

    private static final String COLLECTION_NAME = "classrooms";

    private final Firestore firestore;

    private CollectionReference getCollection() {
        return firestore.collection(COLLECTION_NAME);
    }

    public String saveClassRoom(ClassRoom classroom) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(classroom.getClassroomId()).set(classroom);
        return future.get().getUpdateTime().toString();
    }

    public ClassRoom getClassRoomById(String classroomId) throws ExecutionException, InterruptedException {
        return getCollection().document(classroomId).get().get().toObject(ClassRoom.class);
    }

    public List<ClassRoom> getAllClassRooms() throws ExecutionException, InterruptedException {
        return getCollection().get().get()
                .getDocuments()
                .stream()
                .map(doc -> doc.toObject(ClassRoom.class))
                .collect(Collectors.toList());
    }

    public String updateClassRoom(ClassRoom classroom) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(classroom.getClassroomId()).set(classroom);
        return future.get().getUpdateTime().toString();
    }

    public String deleteClassRoom(String classroomId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(classroomId).delete();
        return future.get().getUpdateTime().toString();
    }
}
