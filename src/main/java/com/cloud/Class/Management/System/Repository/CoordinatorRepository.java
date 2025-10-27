package com.cloud.Class.Management.System.Repository;


import com.cloud.Class.Management.System.Entity.Coordinator;
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
public class CoordinatorRepository {
    private final String COLLECTION_NAME = "coordinators";

    private CollectionReference getCollection() {
        Firestore db = FirestoreClient.getFirestore();
        return db.collection(COLLECTION_NAME);
    }

    public String saveCoordinator(Coordinator coordinator) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(coordinator.getCoordinatorId()).set(coordinator);
        return future.get().getUpdateTime().toString();
    }

    public Coordinator getCoordinatorById(String coordinatorId) throws ExecutionException, InterruptedException {
        return getCollection().document(coordinatorId).get().get().toObject(Coordinator.class);
    }

    public List<Coordinator> getAllCoordinators() throws ExecutionException, InterruptedException {
        return getCollection().get().get()
                .getDocuments()
                .stream()
                .map(doc -> doc.toObject(Coordinator.class))
                .collect(Collectors.toList());
    }

    public String updateCoordinator(Coordinator coordinator) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(coordinator.getCoordinatorId()).set(coordinator);
        return future.get().getUpdateTime().toString();
    }

    public String deleteCoordinator(String coordinatorId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(coordinatorId).delete();
        return future.get().getUpdateTime().toString();
    }
}
