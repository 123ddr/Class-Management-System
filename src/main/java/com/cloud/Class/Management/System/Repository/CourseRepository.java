package com.cloud.Class.Management.System.Repository;


import com.cloud.Class.Management.System.Entity.Course;
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
public class CourseRepository {
    private final String COLLECTION_NAME = "courses";

    private CollectionReference getCollection() {
        Firestore db = FirestoreClient.getFirestore();
        return db.collection(COLLECTION_NAME);
    }

    public String saveCourse(Course course) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(course.getCourseId()).set(course);
        return future.get().getUpdateTime().toString();
    }

    public Course getCourseById(String courseId) throws ExecutionException, InterruptedException {
        return getCollection().document(courseId).get().get().toObject(Course.class);
    }

    public List<Course> getAllCourses() throws ExecutionException, InterruptedException {
        return getCollection().get().get()
                .getDocuments()
                .stream()
                .map(doc -> doc.toObject(Course.class))
                .collect(Collectors.toList());
    }

    public String updateCourse(Course course) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(course.getCourseId()).set(course);
        return future.get().getUpdateTime().toString();
    }

    public String deleteCourse(String courseId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(courseId).delete();
        return future.get().getUpdateTime().toString();
    }
}
