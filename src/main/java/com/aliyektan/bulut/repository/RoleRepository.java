package com.aliyektan.bulut.repository;

import com.aliyektan.bulut.entity.Role;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(@NonNull @NotNull @NotBlank String name);

}
