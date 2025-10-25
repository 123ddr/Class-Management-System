package com.cloud.Class.Management.System.Repository;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class FirebaseRepository {

    @Autowired
    private Firestore firestore;

    public <T> String save(T entity, String collectionName) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collectionName).document();
        String generatedId = docRef.getId();

        // Set the ID field if it exists
        try {
            entity.getClass().getMethod("setId", String.class).invoke(entity, generatedId);
        } catch (Exception e) {
            // If no setId method, continue without setting ID
        }

        ApiFuture<WriteResult> result = docRef.set(entity);
        result.get();
        return generatedId;
    }

    public <T> T findById(String id, String collectionName, Class<T> clazz) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collectionName).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            T entity = document.toObject(clazz);
            // Set the ID field if it exists
            try {
                entity.getClass().getMethod("setId", String.class).invoke(entity, document.getId());
            } catch (Exception e) {
                // If no setId method, continue without setting ID
            }
            return entity;
        }
        return null;
    }

    public <T> List<T> findAll(String collectionName, Class<T> clazz) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection(collectionName).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<T> entities = new ArrayList<>();

        for (QueryDocumentSnapshot document : documents) {
            T entity = document.toObject(clazz);
            // Set the ID field if it exists
            try {
                entity.getClass().getMethod("setId", String.class).invoke(entity, document.getId());
            } catch (Exception e) {
                // If no setId method, continue without setting ID
            }
            entities.add(entity);
        }
        return entities;
    }

    public <T> void update(String id, T entity, String collectionName) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collectionName).document(id);
        ApiFuture<WriteResult> result = docRef.set(entity);
        result.get();
    }

    public void delete(String id, String collectionName) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collectionName).document(id);
        ApiFuture<WriteResult> result = docRef.delete();
        result.get();
    }
}