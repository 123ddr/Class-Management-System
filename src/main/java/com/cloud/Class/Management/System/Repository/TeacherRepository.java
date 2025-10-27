package com.cloud.Class.Management.System.Repository;


import com.cloud.Class.Management.System.Entity.Teacher;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class TeacherRepository {
    private final String COLLECTION_NAME = "teachers";

    private CollectionReference getCollection() {
        Firestore db = FirestoreClient.getFirestore();
        return db.collection(COLLECTION_NAME);
    }

    public String saveTeacher(Teacher teacher) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(teacher.getTeacherId()).set(teacher);
        return future.get().getUpdateTime().toString();
    }

    public Teacher getTeacherById(String teacherId) throws ExecutionException, InterruptedException {
        return getCollection().document(teacherId).get().get().toObject(Teacher.class);
    }

    public List<Teacher> getAllTeachers() throws ExecutionException, InterruptedException {
        return getCollection().get().get()
                .getDocuments()
                .stream()
                .map(doc -> doc.toObject(Teacher.class))
                .collect(Collectors.toList());
    }

    public String updateTeacher(Teacher teacher) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(teacher.getTeacherId()).set(teacher);
        return future.get().getUpdateTime().toString();
    }

    public String deleteTeacher(String teacherId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(teacherId).delete();
        return future.get().getUpdateTime().toString();
    }
}
