package com.nuitriapp.equilibro.service;

import com.nuitriapp.equilibro.model.Forum;
import com.nuitriapp.equilibro.model.Message;
import com.nuitriapp.equilibro.repository.ForumRepository;
import com.nuitriapp.equilibro.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private MessageRepository messageRepository;

    //
    public Forum ajouterSujet(Forum forum) {
        return forumRepository.save(forum);
    }

    public List<Forum> obtenirTousLesSujets() {
        return forumRepository.findAll();
    }

    public Message ajouterMessage(Long forumId, Message message) {
        // Trouver le forum par ID
        Forum forum = forumRepository.findById(forumId)
                .orElseThrow(() -> new RuntimeException("Forum non trouvé avec l'ID: " + forumId));

        // Vérifier si le message a un utilisateur associé
        if (message.getUtilisateur() == null) {
            throw new RuntimeException("Le message doit être associé à un utilisateur.");
        }

        // Associer le message au forum
        forum.getMessages().add(message);

        // Optionnel : Si vous avez besoin d'ajouter la relation inverse dans le message
        message.setForum(forum);

        // Sauvegarde le forum pour persister le message
        forumRepository.save(forum);

        // Retourne le message créé
        return message;
    }


    public Forum modifierSujet(Long id, Forum forumDetails) {
        Forum forum = forumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forum not found"));
        forum.setSujet(forumDetails.getSujet());
        return forumRepository.save(forum);
    }

    public void supprimerSujet(Long id) {
        if (forumRepository.existsById(id)) {
            forumRepository.deleteById(id);
        } else {
            throw new RuntimeException("Forum not found");
        }
    }

    public Message modifierMessage(Long messageId, Message messageDetails) {
        Message message =messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        message.setContenu(messageDetails.getContenu());
        return messageRepository.save(message);
    }

    public void supprimerMessage(Long messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
        } else {
            throw new RuntimeException("Message not found");
        }
    }

}
