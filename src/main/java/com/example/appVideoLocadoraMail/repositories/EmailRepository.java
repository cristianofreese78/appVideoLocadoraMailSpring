package com.example.appVideoLocadoraMail.repositories;

import com.example.appVideoLocadoraMail.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
