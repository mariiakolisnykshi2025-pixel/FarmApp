package Service;

import Entities.Farm;
import Repository.FarmRepository;
import Repository.FieldRepository;
import Repository.StorageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

class FacadeTest {

    private FarmRepository farmRepository;
    private FieldRepository fieldRepository;
    private StorageRepository storageRepository;
    private TransferService transferService;
    private Facade facade;

    @BeforeEach
    void setUp() {
        farmRepository = Mockito.mock(FarmRepository.class);
        fieldRepository = Mockito.mock(FieldRepository.class);
        storageRepository = Mockito.mock(StorageRepository.class);
        transferService = Mockito.mock(TransferService.class);

        facade = new Facade(farmRepository, fieldRepository,
                storageRepository, transferService, new ObjectMapper());
    }

    @Test
    void testSaveFarm() {
        Map<String, Object> data = Map.of("name", "New Farm");
        facade.save("farms", data, false);

        Mockito.verify(farmRepository).save(any(Farm.class));
    }

    @Test
    void testCalculateProfit() {
        Farm farm = new Farm.FarmBuilder()
                .set_name("Test Farm")
                .set_price(10)
                .set_units(100)
                .set_workers(1)
                .set_salary(3000)
                .build();

        Mockito.when(farmRepository.findById(1)).thenReturn(Optional.of(farm));

        Map<String, Object> result = facade.calculateProfit("farms", 1, 1);

        assertEquals("Test Farm", result.get("name"));
        assert(result.containsKey("net_profit"));
    }
}