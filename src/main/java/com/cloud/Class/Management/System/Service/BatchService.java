package com.cloud.Class.Management.System.Service;


import com.cloud.Class.Management.System.Entity.Batch;
import com.cloud.Class.Management.System.Repository.BatchRepository;
import com.cloud.Class.Management.System.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BatchService {

    private final BatchRepository batchRepository;
    private final CourseRepository courseRepository;

    public BatchService(BatchRepository batchRepository, CourseRepository courseRepository) {
        this.batchRepository = batchRepository;
        this.courseRepository = courseRepository;
    }

    public String createBatch(Batch batch) throws ExecutionException, InterruptedException {
        // Validate course exists
        if (courseRepository.getCourseById(batch.getCourseId()) == null) {
            throw new IllegalArgumentException("Course does not exist!");
        }
        return batchRepository.saveBatch(batch);
    }

    public Batch getBatch(String batchId) throws ExecutionException, InterruptedException {
        return batchRepository.getBatchById(batchId);
    }

    public List<Batch> getAllBatches() throws ExecutionException, InterruptedException {
        return batchRepository.getAllBatches();
    }

    public String updateBatch(Batch batch) throws ExecutionException, InterruptedException {
        // Validate course exists
        if (courseRepository.getCourseById(batch.getCourseId()) == null) {
            throw new IllegalArgumentException("Course does not exist!");
        }
        return batchRepository.updateBatch(batch);
    }

    public String deleteBatch(String batchId) throws ExecutionException, InterruptedException {
        return batchRepository.deleteBatch(batchId);
    }

    // Optional: Get all batches for a course
    public List<Batch> getBatchesByCourse(String courseId) throws ExecutionException, InterruptedException {
        return batchRepository.getAllBatches().stream()
                .filter(b -> b.getCourseId().equals(courseId))
                .toList();
    }
}
