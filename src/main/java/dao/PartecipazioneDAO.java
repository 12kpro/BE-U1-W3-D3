package dao;

import entities.Partecipazione;
import entities.Persona;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

@Slf4j
public class PartecipazioneDAO {
    private final EntityManager em;

    public PartecipazioneDAO(EntityManager em) {
        this.em = em;
    }
    public void save(Partecipazione p) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(p);
            transaction.commit();
            log.info("Persona salvata correttamente:{}", p);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Errore durante il salvataggio della Persona.", e);
            throw e;
        }
    }

    public Partecipazione getById(UUID id) {
        Partecipazione found = em.find(Partecipazione.class, id);
        return found;
    }

    public void delete(UUID id ) {
        Partecipazione p = em.find(Partecipazione.class, id);
        if (p != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(p);
                transaction.commit();
                log.info("Persona eliminata correttamente: {}", p);
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
        Partecipazione p = em.find(Partecipazione.class, id);
        if (p != null) {
            log.info("Persona aggiornata:" + p);
            em.refresh(p);
            log.info("Persona recuperata correttamente:" + p);
        }
    }
}
