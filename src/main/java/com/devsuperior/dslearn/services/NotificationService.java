package com.devsuperior.dslearn.services;

import com.devsuperior.dslearn.DTO.NotificationDTO;
import com.devsuperior.dslearn.entities.Notification;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public Page<NotificationDTO> notificationsForCurrentUser(Pageable pageable) {
        User user = authService.authenticated();
        Page<Notification> page = repository.findByUser(user, pageable);
        return page.map(x -> new NotificationDTO(x));
    }


}
