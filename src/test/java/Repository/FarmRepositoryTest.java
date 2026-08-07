package Repository;

import Entities.Farm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = {Config.AppConfig.class})
@EntityScan(basePackages = "Entities")
@EnableJpaRepositories(basePackages = "Repository")
class FarmRepositoryTest {

    @Autowired
    private FarmRepository farmRepository;

    @Test
    void testSaveAndFindAll() {
        Farm farm = new Farm.FarmBuilder().set_name("Тестова Ферма").build();
        farmRepository.save(farm);

        List<Farm> allFarms = farmRepository.findAll();

        assertEquals(1, allFarms.size());
        assertEquals("Тестова Ферма", allFarms.get(0).get_name());
    }

    @Test
    void testFindById() {
        Farm farm = farmRepository.save(new Farm.FarmBuilder().set_name("Шукаємо мене").build());

        Optional<Farm> retrieved = farmRepository.findById(farm.get_id());

        assertTrue(retrieved.isPresent());
        assertEquals("Шукаємо мене", retrieved.get().get_name());
    }

    @Test
    void testDelete() {
        Farm farm = farmRepository.save(new Farm.FarmBuilder().set_name("На видалення").build());

        farmRepository.deleteById(farm.get_id());

        assertFalse(farmRepository.existsById(farm.get_id()));
    }
}