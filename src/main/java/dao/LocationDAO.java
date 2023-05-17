package dao;

import entities.Evento;
import entities.Location;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

@Slf4j
public class LocationDAO {
    private final EntityManager em;

    public LocationDAO(EntityManager em) {
        this.em = em;
    }
    public void save(Location location) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(location);
            transaction.commit();
            log.info("Location salvata correttamente:{}", location);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Errore durante il salvataggio della location.", e);
            throw e;
        }
    }

    public Location getById(UUID id) {
        Location found = em.find(Location.class, id);
        return found;
    }

    public void delete(UUID id ) {
        Location location = em.find(Location.class, id);
        if (location != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(location);
                transaction.commit();
                log.info("Location eliminata correttamente: {}", location);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                log.error("Errore durante l'eliminazione della location.", e);
                throw e;
            }
        }
    }

    public void refresh(UUID id) {
        Location location = em.find(Location.class, id);
        if (location != null) {
            log.info("Location aggiornata:" + location);
            em.refresh(location);
            log.info("Location recuperata correttamente:" + location);
        }
    }
}
