package dao;

import entities.Persona;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

@Slf4j
public class PersonaDAO {
    private final EntityManager em;

    public PersonaDAO(EntityManager em) {
        this.em = em;
    }
    public void save(Persona persona) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(persona);
            transaction.commit();
            log.info("Persona salvata correttamente:{}", persona);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Errore durante il salvataggio della Persona.", e);
            throw e;
        }
    }

    public Persona getById(UUID id) {
        Persona found = em.find(Persona.class, id);
        return found;
    }

    public void delete(UUID id ) {
        Persona persona = em.find(Persona.class, id);
        if (persona != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(persona);
                transaction.commit();
                log.info("Persona eliminata correttamente: {}", persona);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                log.error("Errore durante l'eliminazione della Persona.", e);
                throw e;
            }
        }
    }

    public void refresh(UUID id) {
        Persona persona = em.find(Persona.class, id);
        if (persona != null) {
            log.info("Persona aggiornata:" + persona);
            em.refresh(persona);
            log.info("Persona recuperata correttamente:" + persona);
        }
    }    
}
