package com.solanki.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solanki.employee.beans.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
