package com.cloud.Class.Management.System.Service;





// BatchRosterMailService.java

import com.cloud.Class.Management.System.Entity.BatchRosterMail;
import com.cloud.Class.Management.System.Repository.FirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BatchRosterMailService {

    private static final String COLLECTION_NAME = "batchRosterMails";

    @Autowired
    private FirebaseRepository firebaseRepository;

    public String createRoster(BatchRosterMail roster) throws ExecutionException, InterruptedException {
        return firebaseRepository.save(roster, COLLECTION_NAME);
    }

    public BatchRosterMail getRoster(String id) throws ExecutionException, InterruptedException {
        return firebaseRepository.findById(id, COLLECTION_NAME, BatchRosterMail.class);
    }

    public List<BatchRosterMail> getAllRosters() throws ExecutionException, InterruptedException {
        return firebaseRepository.findAll(COLLECTION_NAME, BatchRosterMail.class);
    }

    public void updateRoster(String id, BatchRosterMail roster) throws ExecutionException, InterruptedException {
        firebaseRepository.update(id, roster, COLLECTION_NAME);
    }

    public void deleteRoster(String id) throws ExecutionException, InterruptedException {
        firebaseRepository.delete(id, COLLECTION_NAME);
    }
}