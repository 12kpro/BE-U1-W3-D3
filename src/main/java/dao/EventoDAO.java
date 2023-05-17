package dao;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Evento;
import lombok.extern.slf4j.Slf4j;
import utils.TipoEvento;

@Slf4j
public class EventoDAO {
	private final EntityManager em;

	public EventoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Evento e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Evento salvato");
	}

	public Evento getById(UUID id) {
		Evento event = em.find(Evento.class, id);
		return event;
	}

	public void delete(UUID id) {
		Evento event = em.find(Evento.class, id);
		if (event != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(event);
			transaction.commit();
			log.info("Evento,id: {} eliminato!", id);
		}
	}

	public void refresh(UUID id, String titolo, String descrizione, LocalDate EventDate, TipoEvento tipo,
			int partecipanti) {
		Evento event = em.find(Evento.class, id);
		event.setTitolo(titolo);
		event.setDescrizione(descrizione);
		event.setDataEvento(EventDate);
		event.setTipoEvento(tipo);
		event.setNumeroMassimoPartecipanti(partecipanti);
		log.info("Evento id:{} ,aggiornato", id);
		em.refresh(event);
	}
}
