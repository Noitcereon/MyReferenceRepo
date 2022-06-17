package noitcereon.mydemojavaapi.controllers;

import noitcereon.mydemojavaapi.models.ActorPostModel;
import noitcereon.mydemojavaapi.models.ActorReadonly;
import noitcereon.mydemojavaapi.models.entities.Actor;
import noitcereon.mydemojavaapi.repositories.IActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ActorControllerTest {

    private ActorController actorController;
    @Mock
    private IActorRepository mockedRepository;

    @BeforeEach
    void setUp() {
        // The Mockity.when() specifies the behaviour the mocked method should do.
        // In the case below it takes in any Actor class and, via the answer
        // override, returns that same Actor object. You could also do
        // some operations on the arguments before returning it.
        Mockito.lenient().when(mockedRepository.save(any(Actor.class))).then((Answer<Actor>) invocationOnMock -> {
            // The arguments are the ones passed to the .save method of the mocked object
            // in order from left to right (starting at 0).
            return invocationOnMock.getArgument(0);
        });

        actorController = new ActorController(mockedRepository);
    }

    @ParameterizedTest
    @CsvSource({
            // "firstName, lastName, age",
            "Kvothe, Arlidensson, 24", "Ib, Æs, 87"})
    void givenRequestWithActorPostModel_whenExecutingPostActor_thenReturnStatusCode200(ArgumentsAccessor arguments) {
        // Arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        ActorPostModel actorPostModel = new ActorPostModel(arguments.getString(0), arguments.getString(1), arguments.getInteger(2));
        // Act
        ResponseEntity<ActorReadonly> response = actorController.createActor(actorPostModel);
        // Assert
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    void givenRequestForAllActors_whenExecutingGetAllActorsWithContent_thenReturnStatusCode200() {
        // Arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        Set<Actor> mockedRepoResponse = new HashSet<>();
        mockedRepoResponse.add(new Actor());
        Mockito.when(mockedRepository.findAllByIsDeletedIsFalse()).thenReturn(mockedRepoResponse);
        // Act
        ResponseEntity<Set<ActorReadonly>> response = actorController.getAll();
        // Assert
        assertEquals(expectedStatusCode, response.getStatusCode());
    }
    @Test
    void givenRequestForAllActors_whenExecutingGetAllActorsWithNoContent_thenReturnStatusCode204() {
        // Arrange
        HttpStatus expectedStatusCode = HttpStatus.NO_CONTENT;
        // Act
        ResponseEntity<Set<ActorReadonly>> response = actorController.getAll();
        // Assert
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    void givenRequestForSpecificActor_whenExecutingGetSpecificActor_thenReturnStatusCode200() {
        // Arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        String simulatedId = "abc";
        // Act
        Mockito.when(mockedRepository.findByUuidAndIsDeletedIsFalse(simulatedId)).thenReturn(new Actor());
        ResponseEntity<ActorReadonly> response = actorController.getById(simulatedId);
        // Assert
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    void givenRequestForUpdatingActor_whenExecutingPutSpecificActor_thenReturnStatusCode200() {
        // Arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        String simulatedId = "abc";
        // Act
        Mockito.when(mockedRepository.findByUuidAndIsDeletedIsFalse(simulatedId)).thenReturn(new Actor());
        ResponseEntity<ActorReadonly> response = actorController.updateActor(new ActorPostModel(), simulatedId);
        // Assert
        assertEquals(expectedStatusCode, response.getStatusCode());
    }
    @Test
    void givenRequestForUpdatingActor_whenExecutingPutSpecificActor_thenSpecifiedActorIsUpdated() {
        // Arrange
        String expectedId = "1bfae8ca-002c-4b69-8b7c-6b16da1b97ea";
        ActorPostModel expectedActorValues = new ActorPostModel("FirstName", "LastName", 24);
        Actor entity = new Actor();
        entity.setUuid(expectedId); // if the setUuid validation changes to be more strict this could fail.
        // Act
        Mockito.when(mockedRepository.findByUuidAndIsDeletedIsFalse(expectedId)).thenReturn(entity);
        ResponseEntity<ActorReadonly> response = actorController.updateActor(expectedActorValues, expectedId);
        // Assert
        assertNotNull(response.getBody());
        assertEquals(expectedId, response.getBody().uuid);
        assertEquals(expectedActorValues.getFirstName(), response.getBody().firstName);
        assertEquals(expectedActorValues.getLastName(), response.getBody().lastName);
        assertEquals(expectedActorValues.getAge(), response.getBody().age);
    }
    @Test
    void givenRequestForDeletingActor_whenExecutingDeleteSpecificActor_thenReturnStatusCode200() {
        // Arrange
        HttpStatus expectedStatusCode = HttpStatus.OK;
        String simulatedId = "abc";
        Optional<Actor> mockedRepoResponse = Optional.of(new Actor());
        // Act
        Mockito.when(mockedRepository.findById(simulatedId)).thenReturn(mockedRepoResponse);
        ResponseEntity<ActorReadonly> response = actorController.disableActor(simulatedId);
        // Assert
        assertEquals(expectedStatusCode, response.getStatusCode());
    }
}
