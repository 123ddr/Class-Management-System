package com.cloud.Class.Management.System.Repository;


import com.cloud.Class.Management.System.Entity.Batch;
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
public class BatchRepository {

    private final String COLLECTION_NAME = "batches";

    private CollectionReference getCollection() {
        Firestore db = FirestoreClient.getFirestore();
        return db.collection(COLLECTION_NAME);
    }

    public String saveBatch(Batch batch) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(batch.getBatchId()).set(batch);
        return future.get().getUpdateTime().toString();
    }

    public Batch getBatchById(String batchId) throws ExecutionException, InterruptedException {
        return getCollection().document(batchId).get().get().toObject(Batch.class);
    }

    public List<Batch> getAllBatches() throws ExecutionException, InterruptedException {
        return getCollection().get().get()
                .getDocuments()
                .stream()
                .map(doc -> doc.toObject(Batch.class))
                .collect(Collectors.toList());
    }

    public String updateBatch(Batch batch) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(batch.getBatchId()).set(batch);
        return future.get().getUpdateTime().toString();
    }

    public String deleteBatch(String batchId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(batchId).delete();
        return future.get().getUpdateTime().toString();
    }
}
