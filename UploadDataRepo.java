package com.upload.demo.datarepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upload.demo.entity.UploadData;

public interface UploadDataRepo extends JpaRepository<UploadData, Integer> {

}
