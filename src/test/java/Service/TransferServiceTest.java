package Service;

import Entities.Farm;
import Repository.FarmRepository;
import Repository.FieldRepository;
import Repository.StorageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferServiceTest {

    private FarmRepository farmRepository;
    private FieldRepository fieldRepository;
    private StorageRepository storageRepository;
    private TransferService transferService;

    @BeforeEach
    void setUp() {
        farmRepository = mock(FarmRepository.class);
        fieldRepository = mock(FieldRepository.class);
        storageRepository = mock(StorageRepository.class);
        transferService = new TransferService(farmRepository, fieldRepository, storageRepository);
    }

    @Test
    void testTransferWorkersSuccess() {
        Farm sourceFarm = new Farm.FarmBuilder().set_workers(100).build();
        Farm targetFarm = new Farm.FarmBuilder().set_workers(50).build();

        when(farmRepository.findById(1)).thenReturn(Optional.of(sourceFarm));
        when(farmRepository.findById(2)).thenReturn(Optional.of(targetFarm));

        transferService.transferWorkers("farms", 1, 2, 30);

        assertEquals(70, sourceFarm.get_number_of_workers()); // 100 - 30
        assertEquals(80, targetFarm.get_number_of_workers()); // 50 + 30

        verify(farmRepository).save(sourceFarm);
        verify(farmRepository).save(targetFarm);
    }

    @Test
    void testTransferWorkersThrowsExceptionForTooManyPeople() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transferService.transferWorkers("farms", 1, 2, 101);
        });

        assertEquals("Забагато людей!", exception.getMessage());

        verifyNoInteractions(farmRepository);
    }

    @Test
    void testTransferWorkersNotEnoughPeople() {
        Farm sourceFarm = new Farm.FarmBuilder().set_workers(10).build();
        when(farmRepository.findById(1)).thenReturn(Optional.of(sourceFarm));
        when(farmRepository.findById(2)).thenReturn(Optional.of(new Farm()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transferService.transferWorkers("farms", 1, 2, 20);
        });

        assertEquals("Мало людей!", exception.getMessage());
    }

    @Test
    void testInvalidTableName() {
        assertThrows(IllegalArgumentException.class, () -> {
            transferService.transferWorkers("aliens", 1, 2, 10);
        });
    }
}