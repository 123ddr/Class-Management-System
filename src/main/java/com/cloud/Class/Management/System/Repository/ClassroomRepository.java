package com.cloud.Class.Management.System.Repository;

import com.cloud.Class.Management.System.Entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    List<Classroom> findByBuildingId(Long buildingId);

    List<Classroom> findByBuildingIdAndCapacityGreaterThanEqual(Long buildingId, Integer capacity);

    List<Classroom> findByBuildingNumber(String buildingNumber);

    List<Classroom> findByBuildingNumberAndCapacityGreaterThanEqual(String buildingNumber, Integer capacity);
}
