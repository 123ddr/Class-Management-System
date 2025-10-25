package com.cloud.Class.Management.System.Service;



import com.cloud.Class.Management.System.Entity.Batch;
import com.cloud.Class.Management.System.Repository.FirebaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BatchService {

    private static final String COLLECTION_NAME = "batches";

    @Autowired
    private FirebaseRepository firebaseRepository;

    public String createBatch(Batch batch) throws ExecutionException, InterruptedException {
        return firebaseRepository.save(batch, COLLECTION_NAME);
    }

    public Batch getBatch(String id) throws ExecutionException, InterruptedException {
        return firebaseRepository.findById(id, COLLECTION_NAME, Batch.class);
    }

    public List<Batch> getAllBatches() throws ExecutionException, InterruptedException {
        return firebaseRepository.findAll(COLLECTION_NAME, Batch.class);
    }

    public void updateBatch(String id, Batch batch) throws ExecutionException, InterruptedException {
        firebaseRepository.update(id, batch, COLLECTION_NAME);
    }

    public void deleteBatch(String id) throws ExecutionException, InterruptedException {
        firebaseRepository.delete(id, COLLECTION_NAME);
    }
}