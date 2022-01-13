package cinema.services;

import cinema.dao.*;
import cinema.entities.Cinema;
import cinema.entities.Place;
import cinema.entities.Salle;
import cinema.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CinemaInitImpl implements ICinemaInitService {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void initVilles() {
        Stream.of("Tunis","sousse","Ariana","Manouba").forEach(villelName->{
            Ville ville = new Ville();
            ville.setName(villelName);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(ville -> {
            Stream.of("Pathe","colisee","parnasse","palace","africa","express").forEach(cinemaName->{
                Cinema cinema = new Cinema();
                cinema.setName(cinemaName);
                cinema.setVille(ville);
                cinema.setNombreSalles(3+(int)(Math.random()*7));
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i=0; i<cinema.getNombreSalles();i++){
                Salle salle = new Salle();
                salle.setName("Salle "+(i+1));
                salle.setCinema(cinema);
                salle.setNombrePlace(20+(int)(Math.random()*20));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i=0;i<salle.getNombrePlace();i++){
                Place place = new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {

    }

    @Override
    public void initCategories() {

    }

    @Override
    public void initFilms() {

    }

    @Override
    public void initProjections() {

    }

    @Override
    public void initTickets() {

    }
}
