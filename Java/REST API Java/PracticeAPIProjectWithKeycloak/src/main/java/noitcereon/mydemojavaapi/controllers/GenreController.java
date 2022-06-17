package noitcereon.mydemojavaapi.controllers;

import noitcereon.mydemojavaapi.models.entities.Genre;
import noitcereon.mydemojavaapi.repositories.IGenreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private final IGenreRepository genreRepo;

    public GenreController(IGenreRepository genreRepository) {
        genreRepo = genreRepository;
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody String name) {
        Genre savedGenre = genreRepo.save(new Genre(name));
        return ResponseEntity.ok(savedGenre);
    }

    @GetMapping
    public ResponseEntity<Set<Genre>> getAll() {
        return ResponseEntity.ok(genreRepo.findAllByIsDeletedIsFalse());
    }

    @GetMapping("{genreId}")
    public ResponseEntity<Genre> getById(@PathVariable String genreId) {
        Genre genre = genreRepo.findByUuidAndIsDeletedIsFalse(genreId);
        return ResponseEntity.ok(genre);
    }

    @PutMapping("{genreId}")
    public ResponseEntity<Genre> updateGenre(@PathVariable String genreId, @RequestBody String name) {
        Genre genre = genreRepo.findByUuidAndIsDeletedIsFalse(genreId);
        genre.setName(name);
        Genre savedGenre = genreRepo.save(genre);
        return ResponseEntity.ok(savedGenre);
    }

    @DeleteMapping("{genreId}")
    public ResponseEntity<Boolean> deleteGenre(@PathVariable String genreId) {
        genreRepo.deleteById(genreId);
        return ResponseEntity.ok(true);
    }
}
