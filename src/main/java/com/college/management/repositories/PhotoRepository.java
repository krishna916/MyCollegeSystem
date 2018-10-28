package com.college.management.repositories;

import com.college.management.model.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<UserPhoto, Long> {
    @Query("select up from UserPhoto up join up.user u where u.id = ?1")
    Optional<UserPhoto> selectByUserId(Long id);
}
