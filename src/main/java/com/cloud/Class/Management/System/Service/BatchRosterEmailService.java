package com.cloud.Class.Management.System.Service;


import com.cloud.Class.Management.System.Entity.BatchRosterEmail;
import com.cloud.Class.Management.System.Repository.BatchRepository;
import com.cloud.Class.Management.System.Repository.BatchRosterEmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BatchRosterEmailService {

    private final BatchRosterEmailRepository repository;
    private final BatchRepository batchRepository;

    public BatchRosterEmailService(BatchRosterEmailRepository repository, BatchRepository batchRepository) {
        this.repository = repository;
        this.batchRepository = batchRepository;
    }

    public String createRoster(BatchRosterEmail roster) throws ExecutionException, InterruptedException {
        if (batchRepository.getBatchById(roster.getBatchId()) == null) {
            throw new IllegalArgumentException("Batch does not exist!");
        }
        return repository.saveRoster(roster);
    }

    public BatchRosterEmail getRoster(String rosterId) throws ExecutionException, InterruptedException {
        return repository.getRosterById(rosterId);
    }

    public List<BatchRosterEmail> getAllRosters() throws ExecutionException, InterruptedException {
        return repository.getAllRosters();
    }

    public String updateRoster(BatchRosterEmail roster) throws ExecutionException, InterruptedException {
        if (batchRepository.getBatchById(roster.getBatchId()) == null) {
            throw new IllegalArgumentException("Batch does not exist!");
        }
        return repository.updateRoster(roster);
    }

    public String deleteRoster(String rosterId) throws ExecutionException, InterruptedException {
        return repository.deleteRoster(rosterId);
    }

    public List<BatchRosterEmail> getRostersByBatch(String batchId) throws ExecutionException, InterruptedException {
        return repository.getByBatchId(batchId);
    }
}
