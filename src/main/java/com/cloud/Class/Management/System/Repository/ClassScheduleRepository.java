package com.cloud.Class.Management.System.Repository;


import com.cloud.Class.Management.System.Entity.ClassSchedule;
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
public class ClassScheduleRepository {

    private static final String COLLECTION_NAME = "class_schedules";

    private final Firestore firestore;

    private CollectionReference getCollection() {
        return firestore.collection(COLLECTION_NAME);
    }

    public String saveClassSchedule(ClassSchedule schedule) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(schedule.getScheduleId()).set(schedule);
        return future.get().getUpdateTime().toString();
    }

    public ClassSchedule getClassScheduleById(String scheduleId) throws ExecutionException, InterruptedException {
        return getCollection().document(scheduleId).get().get().toObject(ClassSchedule.class);
    }

    public List<ClassSchedule> getAllClassSchedules() throws ExecutionException, InterruptedException {
        return getCollection().get().get()
                .getDocuments()
                .stream()
                .map(doc -> doc.toObject(ClassSchedule.class))
                .collect(Collectors.toList());
    }

    public String updateClassSchedule(ClassSchedule schedule) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(schedule.getScheduleId()).set(schedule);
        return future.get().getUpdateTime().toString();
    }

    public String deleteClassSchedule(String scheduleId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(scheduleId).delete();
        return future.get().getUpdateTime().toString();
    }
}
