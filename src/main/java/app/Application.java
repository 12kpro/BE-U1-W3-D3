package app;

import dao.EventoDAO;
import dao.LocationDAO;
import dao.PartecipazioneDAO;
import dao.PersonaDAO;
import entities.Evento;
import entities.Location;
import entities.Partecipazione;
import entities.Persona;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;
import utils.Sesso;
import utils.Stato;
import utils.TipoEvento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

@Slf4j
public class Application {

	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		try {
			EntityManager em = emf.createEntityManager();

			PersonaDAO pDAO = new PersonaDAO(em);
			LocationDAO lDAO = new LocationDAO(em);
			EventoDAO eDAO = new EventoDAO(em);
			PartecipazioneDAO partDAO = new PartecipazioneDAO(em);

			Persona persona = new Persona("Mauro", "Simoni", "spam@12kpro.net", LocalDate.of(1979, 4, 10),
					Sesso.Maschio);
			pDAO.save(persona);


			Location location = new Location("Fontanafredda", "Pordenone");
			lDAO.save(location);

			Evento evento = new Evento("concerto elvenking", "Concerto elvenking Fontanafredda", LocalDate.now(),
					TipoEvento.PUBBLICO, 250, location);
			eDAO.save(evento);

			Partecipazione partecipazione = new Partecipazione(persona, evento,
					Stato.DA_CONFERMARE);
			partDAO.save(partecipazione);
			// log.info(ev.getById(UUID.fromString("9d77551d-f970-4809-8116-3fc64972c8ba")));
			//System.out.println(ev.getById(UUID.fromString("9d77551d-f970-4809-8116-3fc64972c8ba")));
			//ev.delete(UUID.fromString("935ebec4-7598-4ce6-8821-81d564a833d7"));
		} catch (ExceptionInInitializerError e) {
			log.error(e.getMessage());
		}
	}
}
