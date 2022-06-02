package noitcereon.mydemojavaapi.controllers;

import noitcereon.mydemojavaapi.models.ActorPostModel;
import noitcereon.mydemojavaapi.models.ActorReadonly;
import noitcereon.mydemojavaapi.models.entities.Actor;
import noitcereon.mydemojavaapi.repositories.IActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        Mockito.when(mockedRepository.save(any(Actor.class))).then(
                (Answer<Actor>) invocationOnMock -> {
                    // The arguments are the ones passed to the .save method of the mocked object
                    // in order from left to right (starting at 0).
                    return invocationOnMock.getArgument(0);
                });
        actorController = new ActorController(mockedRepository);
    }

    @Test
    void givenRequestWithActorPostModel_whenExecutingPostActor_thenReturnStatusCode200() {
        // Given
        ActorPostModel actorPostModel = new ActorPostModel("Kvote", "Arlidensson", 24);
        // When
        ResponseEntity<ActorReadonly> response = actorController.createActor(actorPostModel);
        // Then
        HttpStatus expectedStatusCode = HttpStatus.OK;
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    void givenRequestForAllActors_whenExecutingGetAllActors_thenReturnStatusCode200() {
        fail("Test not implemented");
    }

    @Test
    void givenRequestForSpecificActor_whenExecutingGetSpecificActor_thenReturnStatusCode200() {
        fail("Test not implemented");
    }

    @Test
    void givenRequestForUpdatingActor_whenExecutingPutSpecificActor_thenReturnStatusCode200() {
        fail("Test not implemented");
    }

    @Test
    void givenRequestForDeletingActor_whenExecutingDeleteSpecificActor_thenReturnStatusCode200() {
        fail("Test not implemented");
    }
}
