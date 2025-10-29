package com.cloud.Class.Management.System.Repository;


import com.cloud.Class.Management.System.Entity.BatchRosterEmail;
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
public class BatchRosterEmailRepository {

    private static final String COLLECTION_NAME = "batch_roster_emails";

    private final Firestore firestore;

    private CollectionReference getCollection() {
        return firestore.collection(COLLECTION_NAME);
    }

    public String saveRoster(BatchRosterEmail roster) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(roster.getRosterId()).set(roster);
        return future.get().getUpdateTime().toString();
    }

    public BatchRosterEmail getRosterById(String rosterId) throws ExecutionException, InterruptedException {
        return getCollection().document(rosterId).get().get().toObject(BatchRosterEmail.class);
    }

    public List<BatchRosterEmail> getAllRosters() throws ExecutionException, InterruptedException {
        return getCollection().get().get()
                .getDocuments()
                .stream()
                .map(doc -> doc.toObject(BatchRosterEmail.class))
                .collect(Collectors.toList());
    }

    public String updateRoster(BatchRosterEmail roster) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(roster.getRosterId()).set(roster);
        return future.get().getUpdateTime().toString();
    }

    public String deleteRoster(String rosterId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(rosterId).delete();
        return future.get().getUpdateTime().toString();
    }

    // Optional: Get roster by batch
    public List<BatchRosterEmail> getByBatchId(String batchId) throws ExecutionException, InterruptedException {
        return getAllRosters().stream()
                .filter(r -> r.getBatchId().equals(batchId))
                .toList();
    }
}
