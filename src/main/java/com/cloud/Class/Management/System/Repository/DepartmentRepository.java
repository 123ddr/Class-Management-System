package com.cloud.Class.Management.System.Repository;


import com.cloud.Class.Management.System.Entity.Department;
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
public class DepartmentRepository {
    private final String COLLECTION_NAME = "departments";

    private CollectionReference getCollection() {
        Firestore db = FirestoreClient.getFirestore();
        return db.collection(COLLECTION_NAME);
    }

    public String saveDepartment(Department department) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(department.getDepartmentId()).set(department);
        return future.get().getUpdateTime().toString();
    }

    public Department getDepartmentById(String departmentId) throws ExecutionException, InterruptedException {
        return getCollection().document(departmentId).get().get().toObject(Department.class);
    }

    public List<Department> getAllDepartments() throws ExecutionException, InterruptedException {
        return getCollection().get().get()
                .getDocuments()
                .stream()
                .map(doc -> doc.toObject(Department.class))
                .collect(Collectors.toList());
    }

    public String updateDepartment(Department department) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(department.getDepartmentId()).set(department);
        return future.get().getUpdateTime().toString();
    }

    public String deleteDepartment(String departmentId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(departmentId).delete();
        return future.get().getUpdateTime().toString();
    }
}
