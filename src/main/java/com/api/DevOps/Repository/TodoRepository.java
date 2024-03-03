package com.api.DevOps.Repository;

import com.api.DevOps.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository <Task, Long>{

}

